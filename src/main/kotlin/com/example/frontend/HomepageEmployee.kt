package com.example.frontend

import com.example.database.dao.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import java.sql.DriverManager
import kotlin.math.abs

fun Route.getHomepageEmployee() {
    val daoAircraftBrand: DAOAircraftBrand = DAOAircraftBrandImpl()
    val daoAirportDirectory: DAOAirportDirectory = DAOAirportDirectoryImpl()
    val daoCarrier: DAOCarrier = DAOCarrierImpl()
    val daoFlight: DAOFlight = DAOFlightImpl()
    val daoLocationType: DAOLocationType = DAOLocationTypeImpl()
    val daoPassengers: DAOPassengers = DAOPassengersImpl()

    route("/user") {
        route("/employee_name") {
            get("") {
                call.respond(FreeMarkerContent("employeepage.ftl", null))
                val params = call.receiveParameters()
            }
            post("/next") {
                call.respondRedirect("/user/employee_name/add_flight")
            }
            get("/add_flight") {
                call.respond(FreeMarkerContent("flight.ftl", mapOf("data" to getAirport(), "carrier" to getCarrier(), "brands" to getBrand())))
            }
            post("/add_new_flight") {
                val params = call.receiveParameters()
                val pointOfDeparture = params["Point_of_departure"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val pointOfArrival = params["Point_of_arrival"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val departureTime = params["Departure_time"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val arrivalTime = params["Arrival_time"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val distance = params["Distance"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val carrier = params["Carrier_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val brand = params["Brand_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                if (daoFlight.flight(pointOfDeparture.toInt() + 1,
                        pointOfArrival.toInt() + 1,
                        departureTime,
                        arrivalTime,
                        distance.toInt(),
                        carrier.toInt() + 1,
                        brand.toInt() + 1) == null) {

                    daoFlight.apply {
                        runBlocking {
                            addNewFlight(pointOfDeparture.toInt()+1,
                                pointOfArrival.toInt() + 1,
                                departureTime,
                                arrivalTime,
                                pars(departureTime, arrivalTime),
                                distance.toInt(),
                                carrier.toInt() + 1,
                                brand.toInt() + 1)
                        }
                    }

                }
                call.respondRedirect("/user/employee_name/add_flight")
            }
            post("/add_brand") {
                val params = call.receiveParameters()
                val brand = params["brand"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val coastFactor = params["coast_factor"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                if (daoAircraftBrand.aircraftBrand(brand) == null) {
                    daoAircraftBrand.apply {
                        runBlocking {
                            daoAircraftBrand.addNewAircraftBrand(brand, coastFactor.toDouble())
                        }
                    }
                }
                else daoAircraftBrand.updateBrand(brand, coastFactor.toDouble())
                call.respondRedirect("/user/employee_name")
            }
            post("/add_airport") {
                val params = call.receiveParameters()
                val city = params["city"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val airportName = params["airport_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                if (daoAirportDirectory.airportDirectory(airportName) == null) {
                    daoAirportDirectory.apply {
                        runBlocking {
                            daoAirportDirectory.addNewAirportDirectory(city, airportName)
                        }
                    }
                }
                call.respondRedirect("/user/employee_name")
            }
            post("/add_carrier") {
                val params = call.receiveParameters()
                val carrier = params["carrier"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                if (daoCarrier.carrier(carrier) == null) {
                    daoCarrier.apply {
                        runBlocking {
                            daoCarrier.addNewCarrier(carrier)
                        }
                    }
                }
                call.respondRedirect("/user/employee_name")
            }
            post("/add_place") {
                val params = call.receiveParameters()
                val place = params["place"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val seatPrice = params["seat_price"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                if (daoLocationType.locationType(place) == null) {
                    daoLocationType.apply {
                        runBlocking {
                            daoLocationType.addNewLocationType(place, seatPrice.toFloat())
                        }
                    }
                }
                else daoLocationType.updateLocationType(place, seatPrice.toFloat())
                call.respondRedirect("/user/employee_name")
            }
        }

    }
}

fun Application.getHomepageEmployeeRouting() {
    routing { getHomepageEmployee() }
}

fun pars(timeDeparture: String, timeArrival: String): String {
    var hourDeparture = ""
    var hourArrival = ""
    var minutesDeparture = ""
    var minutesArrival = ""
    for (i in 0..1) {
        hourDeparture += timeDeparture[i]
        hourArrival += timeArrival[i]
    }
    for (i in 3..4) {
        minutesDeparture += timeDeparture[i]
        minutesArrival += timeArrival[i]
    }
    if (hourDeparture.toInt() < 10)
        hourDeparture = "0$hourDeparture"
    if (hourArrival.toInt() < 10)
        hourArrival = "0$hourArrival"
    if (minutesDeparture.toInt() < 10)
        minutesDeparture = "0$minutesDeparture"
    if (hourDeparture.toInt() < 10)
        minutesArrival = "0$minutesArrival"

    var hourTime = abs(hourArrival.toInt() - hourDeparture.toInt()).toString()
    if (hourTime.toInt() < 10)
        hourTime = "0$hourTime"
    var minutesTime = abs(minutesArrival.toInt() - minutesDeparture.toInt()).toString()
    if (minutesTime.toInt() < 10) {
        minutesTime = "0$minutesTime"
    }

    return "${hourTime}:${minutesTime}"
}

fun getAirport(): MutableList<String> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val airportList: MutableList<String> = mutableListOf()

    val sqlRequestCodeAirportDirectory = """
            select * from airportsdirectory
        """.trimIndent()
    val queryRequestCode = database.prepareStatement(sqlRequestCodeAirportDirectory)
    val resultRequestCode = queryRequestCode.executeQuery()
    while (resultRequestCode.next()) {
        airportList.add(resultRequestCode.getString(3))
    }
    return airportList
}

fun getCarrier(): MutableList<String> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val carrierList: MutableList<String> = mutableListOf()

    val sqlRequestCodeAirportDirectory = """
            select * from carriers
        """.trimIndent()
    val queryRequestCode = database.prepareStatement(sqlRequestCodeAirportDirectory)
    val resultRequestCode = queryRequestCode.executeQuery()
    while (resultRequestCode.next()) {
        carrierList.add(resultRequestCode.getString(2))
    }
    return carrierList
}

fun getBrand(): MutableList<String> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val brandList: MutableList<String> = mutableListOf()

    val sqlRequestCodeAirportDirectory = """
            select * from aircraftbrands
        """.trimIndent()
    val queryRequestCode = database.prepareStatement(sqlRequestCodeAirportDirectory)
    val resultRequestCode = queryRequestCode.executeQuery()
    while (resultRequestCode.next()) {
        brandList.add(resultRequestCode.getString(2))
    }
    return brandList
}