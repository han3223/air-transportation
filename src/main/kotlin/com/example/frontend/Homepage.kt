package com.example.frontend

import com.example.database.dao.DAOUser
import com.example.database.dao.DAOUserImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun Route.getHomepage() {
    val daoUser: DAOUser = DAOUserImpl()

    route("") {
        get("") {
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
        post("/") {
            val params = call.receiveParameters()
            val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val phone = params["phone"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            daoUser.apply {
                runBlocking {
                    addNewUser(email, password, phone)
                }
            }
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
        post("/user") {
            val params = call.receiveParameters()
            val login = params["email_login"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password_login"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            when(daoUser.getLoginAndPasswordUser(login, password)) {
                "user" -> call.respondRedirect("/user/user_name")
                "admin" -> call.respondRedirect("/user/admin_name")
                "employee" -> call.respondRedirect("user/employee_name")
            }
        }
    }
}

fun Application.getHomepageRouting() {
    routing { getHomepage() }
}