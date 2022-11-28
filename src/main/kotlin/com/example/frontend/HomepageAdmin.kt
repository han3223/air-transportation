package com.example.frontend

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getHomepageAdmin() {
    route("/user") {
        get("/admin_name") {
            call.respond(FreeMarkerContent("admin_page.ftl", null))
        }
    }
}

fun Application.getHomepageAdminRouting() {
    routing { getHomepageAdmin() }
}
