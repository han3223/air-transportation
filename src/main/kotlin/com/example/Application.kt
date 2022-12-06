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
import java.io.File
import java.util.*

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


fun main() {
    embeddedServer(
        Netty,
        watchPaths = listOf("airtransportationTest"),
        module = Application::myapp,
        host = "localhost",
        port = 8080,
    ).start(wait = true)
}

