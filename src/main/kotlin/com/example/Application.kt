package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.configureRouting
import com.example.plugins.configureTemplating
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*


fun Application.myapp() {
    configureRouting()
    configureTemplating()
    DatabaseFactory.init()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

data class CustomPrincipal(val userName: String, val realm: String) : Principal

fun main() {
    embeddedServer(
        Netty,
        watchPaths = listOf("airtransportationTest"),
        module = Application::myapp,
        host = "localhost",
        port = 8080,
    ).start(wait = true)
}

