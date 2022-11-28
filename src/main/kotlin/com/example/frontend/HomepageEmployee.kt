package com.example.frontend

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getHomepageEmployee() {
    route("/user") {
        get("/employee_name") {
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
    }
}

fun Application.getHomepageEmployeeRouting() {
    routing { getHomepageUser() }
}