package com.example.frontend

import com.example.database.dao.*
import com.example.database.dataClass.AircraftBrand
import com.example.database.dataClass.AirportDirectory
import com.example.database.dataClass.Carrier
import com.example.database.dataClass.Flight
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.coroutines.runBlocking
import java.sql.DriverManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Route.logIn() {
    val daoLocationType: DAOLocationType = DAOLocationTypeImpl()
    val daoPassengers: DAOPassengers = DAOPassengersImpl()
    val daoTicket: DAOTicket = DAOTicketImpl()

        route("/{first_name}") {
            route("/{last_name}") {
                get("") {
                    val firstName = call.parameters.getOrFail<String>("first_name")
                    val lastName = call.parameters.getOrFail<String>("last_name")
                    call.respond(FreeMarkerContent("homepage.ftl", mapOf("first_name" to firstName, "last_name" to lastName)))
                }

                post("/buy_flight") {
                    val firstName = call.parameters.getOrFail<String>("first_name")
                    val lastName = call.parameters.getOrFail<String>("last_name")

                    val params = call.receiveParameters()
                    var departure = params["departure"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                    var arrival = params["arrival"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val date = params["date"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                    departure = checkAirport(departure)
                    arrival = checkAirport(arrival)

                    if (departure == "" || arrival == "") {
                        call.respond(FreeMarkerContent("homepage.ftl", null))
                    }
                    else {
                        val locationType = daoLocationType.allLocationType()
                        val list = getListFlights()
                        val flightList = list[0]
                        val carrierList = list[1]
                        val brandList = list[2]

                        if (flightList.isNotEmpty()) {
                            call.respond(FreeMarkerContent("homepage.ftl", mapOf(
                                "first_name" to firstName,
                                "last_name" to lastName,
                                "flights" to flightList,
                                "flight" to flightList,
                                "city_departure" to departure,
                                "city_arrival" to arrival,
                                "date_departure" to date,
                                "time" to "5:34",
                                "locations" to locationType,
                                "location" to locationType,
                                "carriers" to carrierList,
                                "carrier" to carrierList,
                                "brands" to brandList,
                                "brand" to brandList), "")
                            )
                        }
                    }
                }
            }
        }


    post("/buy_flight") {
        val params = call.receiveParameters()
        var departure = params["departure"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        var arrival = params["arrival"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val date = params["date"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        departure = checkAirport(departure)
        arrival = checkAirport(arrival)

        if (departure == "" || arrival == "") {
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
        else {
            val locationType = daoLocationType.allLocationType()
            val list = getListFlights()
            val flightList = list[0]
            val carrierList = list[1]
            val brandList = list[2]

            if (flightList.isNotEmpty()) {
                call.respond(FreeMarkerContent("homepage.ftl", mapOf(
                    "flights" to flightList,
                    "flight" to flightList,
                    "city_departure" to departure,
                    "city_arrival" to arrival,
                    "date_departure" to date,
                    "time" to "5:34",
                    "locations" to locationType,
                    "location" to locationType,
                    "carriers" to carrierList,
                    "carrier" to carrierList,
                    "brands" to brandList,
                    "brand" to brandList), "")
                )
            }
        }
    }

    post("/add_ticket_to_the_database") {
        val params = call.receiveParameters()
        // passenger
        val firstName = params["first_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val lastName = params["last_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val middleName = params["middle_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val passportSeries = params["passport_series"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val passportId = params["passport_id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        if (daoPassengers.passenger(passportSeries.toInt(), passportId.toInt()) == null) {
            daoPassengers.apply {
                runBlocking {
                    addNewPassengers(lastName, "$firstName $middleName", passportSeries.toInt(), passportId.toInt())
                }
            }
        }

        // ticket
        val passenger = daoPassengers.passenger(passportSeries.toInt(), passportId.toInt())!!.id_passenger

        val flightNumber = params["flight_number"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val seatCode = params["seat_category_code"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val brandId = params["brand_id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val carrierId = params["carrier_id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        var ticketPrice = params["ticket_price"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val departureDate = params["departure_date"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val arrivalDate = params["arrival_date"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val distance = params["distance"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        val readingFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateDeparture = LocalDate.parse(departureDate, readingFormatter)
        val dateArrival = LocalDate.parse(arrivalDate, readingFormatter)

        ticketPrice = ticketPrice.filter { !it.isWhitespace() }.replace(',', '.')
        daoTicket.apply {
            runBlocking {
                daoTicket.addNewTicket(flightNumber.toInt(), seatCode.toInt(), passenger!!, brandId.toInt(), carrierId.toInt(), ticketPrice.toFloat(), dateDeparture, dateArrival, distance.toInt())
            }
        }
    }
}

fun getListFlights(): Array<List<Any>> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    var arrayFlight = arrayOf<List<Any>>()

    val flightList = mutableListOf<Flight>()
    val carrierList = mutableListOf<Carrier>()
    val brandList = mutableListOf<AircraftBrand>()
    val airportDirectoryDepartureList = mutableListOf<AirportDirectory>()
    val airportDirectoryArrivalList = mutableListOf<AirportDirectory>()

    val sqlRequestAll = """
                select * from flights
                    inner join carriers ON carriers."ID_carrier" = flights."Carrier_id"
                    inner join aircraftbrands ON aircraftbrands."ID_brand" = flights."Brand_id"
                    inner join airportsdirectory ON airportsdirectory."Code_airport" = flights."Point_of_departure"
                    inner join airportsdirectory airportsdirectory2 ON airportsdirectory2."Code_airport" = flights."Point_of_arrival";
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    while (resultRequestAll.next()) {
        val number = resultRequestAll.getInt(1)
        val departureId = resultRequestAll.getInt(2)
        val arrivalId = resultRequestAll.getInt(3)
        val departureTime = resultRequestAll.getString(4)
        val arrivalTime = resultRequestAll.getString(5)
        val distance = resultRequestAll.getInt(6)
        val carrierId = resultRequestAll.getInt(7)
        val brandId = resultRequestAll.getInt(8)
        val carrierIdCarrier = resultRequestAll.getInt(9)
        val companyName = resultRequestAll.getString(10)
        val brandIdBrand = resultRequestAll.getInt(11)
        val brandName = resultRequestAll.getString(12)
        val costFactor = resultRequestAll.getDouble(13)
        val codeAirportDeparture = resultRequestAll.getInt(14)
        val cityDeparture = resultRequestAll.getString(15)
        val airportDeparture = resultRequestAll.getString(16)
        val codeAirportArrival = resultRequestAll.getInt(17)
        val cityArrival = resultRequestAll.getString(18)
        val airportArrival = resultRequestAll.getString(19)
        val flight = Flight(number, departureId, arrivalId, departureTime, arrivalTime, distance, carrierId, brandId)
        val carrier = Carrier(carrierIdCarrier, companyName)
        val brand = AircraftBrand(brandIdBrand, brandName, costFactor)
        val airportDirectoryDeparture = AirportDirectory(codeAirportDeparture, cityDeparture, airportDeparture)
        val airportDirectoryArrival = AirportDirectory(codeAirportArrival, cityArrival, airportArrival)
        flightList.add(flight)
        carrierList.add(carrier)
        brandList.add(brand)
        airportDirectoryDepartureList.add(airportDirectoryDeparture)
        airportDirectoryArrivalList.add(airportDirectoryArrival)
    }

    arrayFlight += flightList
    arrayFlight += carrierList
    arrayFlight += brandList

    return arrayFlight
}

fun checkAirport(place: String): String {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")


    val sqlRequestCodeAirportDirectory = """
            select * from airportsdirectory
        """.trimIndent()
    val queryRequestCode = database.prepareStatement(sqlRequestCodeAirportDirectory)
    val resultRequestCode = queryRequestCode.executeQuery()
    while (resultRequestCode.next()) {
        if (place == resultRequestCode.getString(3)) {
            return resultRequestCode.getString(2)
        }
    }
    return ""
}

fun Application.logInRouting() {
    routing { logIn() }
}


