package com.example.plugins

import freemarker.cache.*
import freemarker.core.HTMLOutputFormat
import io.ktor.server.freemarker.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }

//    routing {
//        get("/html-freemarker") {
//            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
//        }
//    }
}

//data class IndexData(val items: Int)
data class IndexData(val item: List<String>)
