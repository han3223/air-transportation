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


fun Route.getHomepageAdmin() {
    val daoUser: DAOUser = DAOUserImpl()

    route("/user") {
        get("/admin_name") {
            val allUser = daoUser.allUser()
            call.respond(FreeMarkerContent("admin_page.ftl", mapOf(
                "users" to allUser, "user" to allUser
            ), ""))
        }
        post("/admin_name") {
            val params = call.receiveParameters()
            val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            var role = params["select_role"] ?: return@post call.respond(HttpStatusCode.BadRequest)

            val allUser = daoUser.allUser()

            when(role) {
                "Обычный пользователь" -> role = "user"
                "Сотрудник" -> role = "employee"
                "Администратор" -> role = "admin"
            }

            daoUser.updateRole(email, role)
            call.respond(FreeMarkerContent("admin_page.ftl", mapOf(
                "users" to allUser, "user" to allUser
            ), ""))
        }
    }
}

fun Application.getHomepageAdminRouting() {
    routing { getHomepageAdmin() }
}
