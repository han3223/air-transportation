package com.example

import com.example.database.DatabaseFactory
import com.example.database.dao.*
import com.example.plugins.configureRouting
import com.example.plugins.configureTemplating
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.myapp() {
    configureRouting()
    configureTemplating()
    DatabaseFactory.init()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val daoTicket: DAOTicket = DAOTicketImpl()

    val daoUser: DAOUser = DAOUserImpl()


//    val dao: DAOFacadeCarrier = DAOCarrierImpl().apply {
//        runBlocking {
//            addNewCarrier("Победа")
//        }
//    }

//    routing {
//        get("/") {
//            call.respond(dao.allCarriers())
//            //call.respond(FreeMarkerContent("index.ftl", mapOf("carrier" to dao.allCarriers())))
//        }
//        post("/") {
//            val formParameters = call.receiveParameters()
//            val name = formParameters.getOrFail("title")
//            val carrier = dao.addNewCarrier(name)
//            call.respondRedirect("/carrier/${carrier?.id}")
//        }
//        get("{id}") {
//            val id = call.parameters.getOrFail<Int>("id").toInt()
//            call.respond(FreeMarkerContent("show.ftl", mapOf("carrier" to dao.carrier(id))))
//        }
//        get("{id}/edit") {
//            val id = call.parameters.getOrFail<Int>("id").toInt()
//            call.respond(FreeMarkerContent("edit.ftl", mapOf("carrier" to dao.carrier(id))))
//        }
//        post("{id}") {
//            val id = call.parameters.getOrFail<Int>("id").toInt()
//            val formParameters = call.receiveParameters()
//            when (formParameters.getOrFail("_action")) {
//                "update" -> {
//                    val name = formParameters.getOrFail("body")
//                    dao.editCarrier(id, name)
//                    call.respondRedirect("/carriers/$id")
//                }
//                "delete" -> {
//                    dao.deleteCarrier(id)
//                    call.respondRedirect("/carriers")
//                }
//            }
//        }
//    }
}


fun main() {
    embeddedServer(
        Netty,
        watchPaths = listOf("airtransportationTest"),
        module = Application::myapp,
        host = "localhost",
        port = 8080,
    ).start(wait = true)
}

