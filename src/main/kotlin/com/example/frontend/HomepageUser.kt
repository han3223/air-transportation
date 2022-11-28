package com.example.frontend

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getHomepageUser() {
    route("/user") {
        get("/user_name") {
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
    }
}

fun Application.getHomepageUserRouting() {
    routing { getHomepageUser() }
}
