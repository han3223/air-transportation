package com.example.frontend

import com.example.database.dao.*
import com.example.database.dataClass.AirportDirectory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

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
                call.respond(FreeMarkerContent("flight.ftl", mapOf("data" to getAirport(), "carrier" to getCarrier())))
            }
            post("/add_new_flight") {
                val params = call.receiveParameters()
                val pointOfDeparture = params["Point_of_departure"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val pointOfArrival = params["Point_of_arrival"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val departureTime = params["Departure_time"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val arrivalTime = params["Arrival_time"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val distance = params["Distance"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val carrier = params["Carrier_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                daoFlight.apply {
                    runBlocking {
                        addNewFlight(pointOfDeparture.toInt()+1,
                            pointOfArrival.toInt() + 1,
                            departureTime, arrivalTime,
                            distance.toInt(),
                            carrier.toInt() + 1)
                    }
                }
                call.respondRedirect("/user/employee_name/add_flight")
            }
            post("/add_brand") {
                val params = call.receiveParameters()
                val brand = params["brand"] ?: return@post call.respond(HttpStatusCode.BadRequest)

                daoAircraftBrand.apply {
                    runBlocking {
                        daoAircraftBrand.addNewAircraftBrand(brand)
                    }
                }
                call.respondRedirect("/user/employee_name")
            }
            post("/add_airport") {
                val params = call.receiveParameters()
                val city = params["city"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val airportName = params["airport_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                daoAirportDirectory.apply {
                    runBlocking {
                        daoAirportDirectory.addNewAirportDirectory(city, airportName)
                    }
                }
                call.respondRedirect("/user/employee_name")
            }
            post("/add_carrier") {
                val params = call.receiveParameters()
                val carrier = params["carrier"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                daoCarrier.apply {
                    runBlocking {
                        daoCarrier.addNewCarrier(carrier)
                    }
                }
                call.respondRedirect("/user/employee_name")
            }
            post("/add_place") {
                val params = call.receiveParameters()
                val place = params["place"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val seatPrice = params["seat_price"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                daoLocationType.apply {
                    runBlocking {
                        daoLocationType.addNewLocationType(place, seatPrice.toFloat())
                    }
                }
                call.respondRedirect("/user/employee_name")
            }
        }

    }
}

fun Application.getHomepageEmployeeRouting() {
    routing { getHomepageEmployee() }
}

suspend fun getAirport(): MutableList<String> {
    val daoAirportDirectory: DAOAirportDirectory = DAOAirportDirectoryImpl()
    val listAirport = daoAirportDirectory.allAirportDirectory()
    val buff = mutableListOf<String>()
    for (i in 0..listAirport.lastIndex) {
        buff.add(listAirport[i].airport_name)
    }
    return buff
}

suspend fun getCarrier(): MutableList<String> {
    val daoCarrier: DAOCarrier = DAOCarrierImpl()
    val listAirport = daoCarrier.allCarriers()
    val buff = mutableListOf<String>()
    for (i in 0..listAirport.lastIndex) {
        buff.add(listAirport[i].company_name)
    }
    return buff
}