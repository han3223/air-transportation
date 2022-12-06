package com.example.frontend

import com.example.database.dao.*
import com.example.database.dataClass.AircraftBrand
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
import org.jetbrains.exposed.sql.Database
import java.sql.DriverManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Route.logIn() {
//    var firstName: String
//    var lastName: String
    val daoAircraftBrand: DAOAircraftBrand = DAOAircraftBrandImpl()
    val daoAirportDirectory: DAOAirportDirectory = DAOAirportDirectoryImpl()
    val daoFlight: DAOFlight = DAOFlightImpl()
    val daoCarrier: DAOCarrier = DAOCarrierImpl()
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
                    val departure = params["departure"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val arrival = params["arrival"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                    val date = params["date"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                    val codeDeparture = daoAirportDirectory.airportDirectory(departure)
                    val codeArrival = daoAirportDirectory.airportDirectory(arrival)


                    if (codeDeparture?.code_airport != null && codeArrival?.code_airport != null) {
                        val flight = daoFlight.flight(codeDeparture.code_airport, codeArrival.code_airport)
                        val locationType = daoLocationType.allLocationType()
                        val carrier = mutableListOf<Carrier>()
                        val cost = mutableListOf<AircraftBrand>()
                        for (i in 0..flight.lastIndex) {
                            carrier.add(daoCarrier.carrier(flight[i].carrier_id)!!)
                            cost.add(daoAircraftBrand.aircraftBrand(flight[i].brand_id)!!)
                        }

                        if (flight.isNotEmpty()) {
                            call.respond(FreeMarkerContent("homepage.ftl", mapOf(
                                "first_name" to firstName,
                                "last_name" to lastName,
                                "flights" to flight,
                                "flight" to flight,
                                "city_departure" to codeDeparture.city,
                                "city_arrival" to codeArrival.city,
                                "date_departure" to date,
                                "time" to "5:34",
                                "locations" to locationType,
                                "location" to locationType,
                                "carriers" to carrier,
                                "carrier" to carrier,
                                "brands" to cost,
                                "brand" to cost), "")
                            )
                        }
                    }
                }
            }
        }


    post("/buy_flight") {
        val params = call.receiveParameters()
        val departure = params["departure"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val arrival = params["arrival"] ?: return@post call.respond(HttpStatusCode.BadRequest)
        val date = params["date"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        val codeDeparture = daoAirportDirectory.airportDirectory(departure)
        val codeArrival = daoAirportDirectory.airportDirectory(arrival)

        if (codeDeparture == null || codeArrival == null) {
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
        else {
//            Class.forName("org.postgresql.Driver")
//            val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")
//            val sql = """
//                select * from flights
//                    inner join carriers ON carriers."ID_carrier" = flights."Carrier_id"
//                    inner join aircraftbrands ON aircraftbrands."ID_brand" = flights."Brand_id"
//                    inner join airportsdirectory ON airportsdirectory."Code_airport" = flights."Point_of_departure"
//                    inner join airportsdirectory airportsdirectory2 ON airportsdirectory2."Code_airport" = flights."Point_of_arrival"
//            """.trimIndent()
//            val query = database.prepareStatement(sql)
//            val result = query.executeQuery()
//            while (result.next()) {
//                val number = result.getInt(1)
//                val departureId = result.getInt(1)
//                val arrivalId = result.getInt(1)
//                val departureTime = result.getString(1)
//                val arrivalTime = result.getString(1)
//                val distance = result.getInt(1)
//                val carrierId = result.getInt(1)
//                val brandId = result.getInt(1)
//                val flight = Flight(number, departureId, arrivalId, departureTime, arrivalTime, distance, carrierId, brandId)
//                println(flight)
//            }


            val flight = daoFlight.flight(codeDeparture.code_airport!!, codeArrival.code_airport!!)
            val locationType = daoLocationType.allLocationType()
            val carrier = mutableListOf<Carrier>()
            val cost = mutableListOf<AircraftBrand>()
            for (i in 0..flight.lastIndex) {
                carrier.add(daoCarrier.carrier(flight[i].carrier_id)!!)
                cost.add(daoAircraftBrand.aircraftBrand(flight[i].brand_id)!!)
            }

            if (flight.isNotEmpty()) {
                call.respond(FreeMarkerContent("homepage.ftl", mapOf(
                    "flights" to flight,
                    "flight" to flight,
                    "city_departure" to codeDeparture.city,
                    "city_arrival" to codeArrival.city,
                    "date_departure" to date,
                    "time" to "5:34",
                    "locations" to locationType,
                    "location" to locationType,
                    "carriers" to carrier,
                    "carrier" to carrier,
                    "brands" to cost,
                    "brand" to cost), "")
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

fun Application.logInRouting() {
    routing { logIn() }
}


