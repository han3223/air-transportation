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
import java.util.Locale

fun Route.getHomepage() {
    val daoUser: DAOUser = DAOUserImpl()

    route("") {
        get("") {
            call.respond(FreeMarkerContent("homepage.ftl", mapOf("first_name" to "Твой", "last_name" to "Профиль")))
        }
        post("/") {
            val params = call.receiveParameters()
            val firstName = params["first_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val lastName = params["last_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val phone = params["phone"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            daoUser.apply {
                runBlocking {
                    addNewUser(firstName, lastName, email, password, phone)
                }
            }
            call.respond(FreeMarkerContent("homepage.ftl", null))
        }
        post("/user") {
            val params = call.receiveParameters()
            val login = params["email_login"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password_login"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val userArray = daoUser.getLoginAndPasswordUser(login, password)
            translate("ivan")
            when(userArray[2]) {
                "user" -> call.respondRedirect("/user/${translate(userArray[0])}/${translate(userArray[1])}")
                "admin" -> call.respondRedirect("/user/admin_name")
                "employee" -> call.respondRedirect("user/employee_name")
            }
        }
    }
}

fun Application.getHomepageRouting() {
    routing { getHomepage() }
}

fun translate(text: String): String {
    var buffText: String = ""
    for (i in 0..text.lastIndex) {
        when(text[i]) {
            'А' -> buffText += "A"
            'Б' -> buffText += "B"
            'В' -> buffText += "V"
            'Г' -> buffText += "G"
            'Д' -> buffText += "D"
            'Е' -> buffText += "E"
            'Ж' -> buffText += "ZH"
            'З' -> buffText += "Z"
            'И' -> buffText += "I"
            'К' -> buffText += "K"
            'Л' -> buffText += "L"
            'М' -> buffText += "M"
            'Н' -> buffText += "N"
            'О' -> buffText += "O"
            'П' -> buffText += "P"
            'Р' -> buffText += "R"
            'С' -> buffText += "S"
            'Т' -> buffText += "T"
            'У' -> buffText += "U"
            'Ф' -> buffText += "F"
            'Х' -> buffText += "H"
            'Ц' -> buffText += "C"
            'Ч' -> buffText += "CH"
            'Ш' -> buffText += "SH"
            'Щ' -> buffText += "SH"
            'Э' -> buffText += "E"
            'Ю' -> buffText += "YU"
            'Я' -> buffText += "YA"

            'а' -> buffText += "a"
            'б' -> buffText += "b"
            'в' -> buffText += "v"
            'г' -> buffText += "g"
            'д' -> buffText += "d"
            'е' -> buffText += "e"
            'ж' -> buffText += "zh"
            'з' -> buffText += "z"
            'и' -> buffText += "i"
            'к' -> buffText += "k"
            'л' -> buffText += "l"
            'м' -> buffText += "m"
            'н' -> buffText += "n"
            'о' -> buffText += "o"
            'п' -> buffText += "p"
            'р' -> buffText += "r"
            'с' -> buffText += "s"
            'т' -> buffText += "t"
            'у' -> buffText += "u"
            'ф' -> buffText += "f"
            'х' -> buffText += "h"
            'ц' -> buffText += "c"
            'ч' -> buffText += "ch"
            'ш' -> buffText += "sh"
            'щ' -> buffText += "sh"
            'э' -> buffText += "e"
            'ю' -> buffText += "yu"
            'я' -> buffText += "ya"
        }
    }
    println(buffText)
    return buffText
}