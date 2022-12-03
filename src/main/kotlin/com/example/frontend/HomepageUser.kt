package com.example.frontend

import com.example.database.dao.*
import com.example.database.dataClass.Carrier
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.logIn() {
//    var firstName: String
//    var lastName: String
    val daoAirportDirectory: DAOAirportDirectory = DAOAirportDirectoryImpl()
    val daoFlight: DAOFlight = DAOFlightImpl()
    val daoCarrier: DAOCarrier = DAOCarrierImpl()
    val daoLocationType: DAOLocationType = DAOLocationTypeImpl()

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
                        for (i in 0..flight.lastIndex) {
                            carrier.add(daoCarrier.carrier(flight[i].carrier_id)!!)
                        }

                        if (flight.isNotEmpty()) {
                            call.respond(FreeMarkerContent("homepage.ftl", mapOf(
                                "first_name" to firstName,
                                "last_name" to lastName,
                                "flights" to flight,
                                "flight" to flight.listIterator().next().departure_time
                                        to flight.listIterator().next().arrival_time,
                                "city_departure" to codeDeparture.city, "city_arrival" to codeArrival.city,
                                "date_departure" to date,
                                "time" to "5:34",
                                "locations" to locationType,
                                "location" to locationType.listIterator().next().place
                                        to locationType.listIterator().next().seat_price_per_kilometers,
                                "flight" to flight.listIterator().next().distance,
                                "carriers" to carrier, "carrier" to carrier.listIterator().next().company_name), "")
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


        if (codeDeparture?.code_airport != null && codeArrival?.code_airport != null) {
            val flight = daoFlight.flight(codeDeparture.code_airport, codeArrival.code_airport)
            val locationType = daoLocationType.allLocationType()
            val carrier = mutableListOf<Carrier>()
            for (i in 0..flight.lastIndex) {
                carrier.add(daoCarrier.carrier(flight[i].carrier_id)!!)
            }

            if (flight.isNotEmpty()) {
                call.respond(FreeMarkerContent("homepage.ftl", mapOf(
                    "flights" to flight,
                    "flight" to flight.listIterator().next().departure_time
                            to flight.listIterator().next().arrival_time,
                    "city_departure" to codeDeparture.city, "city_arrival" to codeArrival.city,
                    "date_departure" to date,
                    "time" to "5:34",
                    "locations" to locationType,
                    "location" to locationType.listIterator().next().place
                            to locationType.listIterator().next().seat_price_per_kilometers,
                    "flight" to flight.listIterator().next().distance,
                    "carriers" to carrier, "carrier" to carrier.listIterator().next().company_name), "")
                )
            }
        }
    }
}

fun Application.logInRouting() {
    routing { logIn() }
}


