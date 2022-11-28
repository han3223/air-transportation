package com.example.plugins

import com.example.frontend.getHomepageAdminRouting
import com.example.frontend.getHomepageEmployeeRouting
import com.example.frontend.getHomepageRouting
import com.example.frontend.getHomepageUserRouting
import io.ktor.server.application.*

fun Application.configureRouting() {
    getHomepageRouting()
    getHomepageUserRouting()
    getHomepageAdminRouting()
    getHomepageEmployeeRouting()
}
