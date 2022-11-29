package com.example.frontend

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.logIn() {
    route("/user") {
        route("/{first_name}") {
            get("/{last_name}") {
//                call.respondText { "hello" }
                val firstName = call.parameters.getOrFail<String>("first_name")
                val lastName = call.parameters.getOrFail<String>("last_name")

                call.respond(FreeMarkerContent("homepage.ftl", mapOf("first_name" to firstName, "last_name" to lastName)))
            }
        }
    }
}

fun Application.logInRouting() {
    routing { logIn() }
}


