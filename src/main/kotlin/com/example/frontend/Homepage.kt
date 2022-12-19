package com.example.frontend

import com.example.database.dao.DAOReview
import com.example.database.dao.DAOReviewImpl
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
    val daoReview: DAOReview = DAOReviewImpl()

    route("") {
        get("") {
            val allReviews = daoReview.allReview()
            call.respond(FreeMarkerContent("homepage.ftl", mapOf("reviews" to allReviews, "review" to allReviews), ""))
        }
        post("/") {
            val params = call.receiveParameters()
            var firstName = params["first_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            var lastName = params["last_name"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val phone = params["phone"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            firstName = translate(firstName)
            lastName = translate(lastName)

            if (daoUser.user(email) == null) {
                println(daoUser.user(email))
                daoUser.apply {
                    runBlocking {
                        addNewUser(firstName, lastName, email, password, phone)
                    }
                }
                call.respond(FreeMarkerContent("homepage.ftl", mapOf("first_name" to firstName, "last_name" to lastName)))
            }
            else
                call.respondText { "Пользователь уже существует!" }
        }
        post("/user") {
            val params = call.receiveParameters()
            val login = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val password = params["password"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val user = daoUser.user(login, password)
            if (user == null)
                println("Пользователя не существует!")
            else {
                println("Пользователь существует.")
                when(user.role) {
                    "user" -> call.respondRedirect("/${user.firstName}/${user.lastName}")
                    "admin" -> call.respondRedirect("/user/admin")
                    "main admin" -> call.respondRedirect("/user/main_admin")
                    "employee" -> call.respondRedirect("/user/employee_name")
                }
            }
        }
    }
}
fun Application.getHomepageRouting() {
    routing { getHomepage() }
}

fun translate(text: String): String {
    var buffText = ""
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

    return if (buffText != "")
        buffText
    else text
}