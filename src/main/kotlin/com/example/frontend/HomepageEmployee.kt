package com.example.frontend

import com.example.database.dao.*
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
        get("/employee_name") {
            call.respond(FreeMarkerContent("employeepage.ftl", null))
        }
        post("/employee_name") {
            val params = call.receiveParameters()
            //brand
            val brand = params["brand"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            daoAircraftBrand.apply {
                runBlocking {
                    daoAircraftBrand.addNewAircraftBrand(brand)
                }
            }
            //airport
            val city = params["city"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val airportName = params["airport_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            daoAirportDirectory.apply {
                runBlocking {
                    daoAirportDirectory.addNewAirportDirectory(city, airportName)
                }
            }
            //carrier
            val carrier = params["carrier"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            daoCarrier.apply {
                runBlocking {
                    daoCarrier.addNewCarrier(carrier)
                }
            }
            //place
            val place = params["place"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val seatPrice = params["seat_price"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            daoLocationType.apply {
                runBlocking {
                    daoLocationType.addNewLocationType(place, seatPrice.toFloat())
                }
            }
        }
    }
}

fun Application.getHomepageEmployeeRouting() {
    routing { getHomepageEmployee() }
}