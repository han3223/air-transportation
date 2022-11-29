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
            call.respond(FreeMarkerContent("admin_page.ftl", null))
        }
        post("/admin_name") {
            val params = call.receiveParameters()
            val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            var role = params["select_role"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            when(role) {
                "Обычный пользователь" -> role = "user"
                "Сотрудник" -> role = "employee"
                "Администратор" -> role = "admin"
            }

            daoUser.updateRole(email, role)
            call.respond(FreeMarkerContent("admin_page.ftl", null))

        }
    }
}

fun Application.getHomepageAdminRouting() {
    routing { getHomepageAdmin() }
}
