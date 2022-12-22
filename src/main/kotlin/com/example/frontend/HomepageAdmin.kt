package com.example.frontend

import com.example.database.dao.DAOUser
import com.example.database.dao.DAOUserImpl
import com.example.database.dataClass.Flight
import com.example.database.dataClass.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.DriverManager


fun Route.getHomepageAdmin() {
    val daoUser: DAOUser = DAOUserImpl()

    route("/user") {
        get("/admin") {
            val allUser = daoUser.allUser()
            call.respond(FreeMarkerContent("admin_page.ftl", mapOf(
                "users" to allUser, "user" to allUser, "status" to "admin",
                "only_users" to onlyUsers(), "only_user" to onlyUsers(),
                "only_admins" to onlyAdmin(), "only_admin" to onlyAdmin(),
                "only_employes" to onlyEmployee(), "only_employee" to onlyEmployee(),
                "counts" to adminUser(), "count" to adminUser(),
                "flights_carriers" to carrierFlight(), "flight_carrier" to carrierFlight(),
                "tickets" to ticketPassenger(), "ticket" to ticketPassenger(),
                "brands" to ticketBrand(), "brand" to ticketBrand(),
                "avg" to avgTicket()
            ), ""))
        }
        get("/main_admin") {
            val allUser = daoUser.allUser()
            call.respond(FreeMarkerContent("admin_page.ftl", mapOf(
                "users" to allUser, "user" to allUser, "admin" to "Администратор", "status" to "main admin"
            ), ""))
        }
        post("/admin_name") {

            val params = call.receiveParameters()
            val email = params["email"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            var role = params["select_role"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            val status = params["status"] ?: return@post call.respond(HttpStatusCode.BadRequest)

            if (daoUser.user(email)!!.role != "main admin") {
                when(role) {
                    "Обычный пользователь" -> role = "user"
                    "Сотрудник" -> role = "employee"
                    "Администратор" -> role = "admin"
                }

                daoUser.updateRole(email, role)
            }

            if (status == "main admin")
                call.respondRedirect("/user/main_admin")
            else call.respondRedirect("/user/admin")

        }
    }
}

fun Application.getHomepageAdminRouting() {
    routing { getHomepageAdmin() }
}

fun onlyUsers(): List<User> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select * from users where "Role" = 'user';
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val userList = mutableListOf<User>()

    while(resultRequestAll.next()) {
        val id = resultRequestAll.getInt(1)
        val firstName = resultRequestAll.getString(2)
        val lastName = resultRequestAll.getString(3)
        val email = resultRequestAll.getString(4)
        val password = resultRequestAll.getString(5)
        val phone = resultRequestAll.getString(6)
        val role = resultRequestAll.getString(7)
        userList.add(User(id, firstName, lastName, email, password, phone, role))
    }
    return userList
}

fun onlyAdmin(): List<User> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select * from users where "Role" = 'admin';
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val adminList = mutableListOf<User>()

    while(resultRequestAll.next()) {
        val id = resultRequestAll.getInt(1)
        val firstName = resultRequestAll.getString(2)
        val lastName = resultRequestAll.getString(3)
        val email = resultRequestAll.getString(4)
        val password = resultRequestAll.getString(5)
        val phone = resultRequestAll.getString(6)
        val role = resultRequestAll.getString(7)
        adminList.add(User(id, firstName, lastName, email, password, phone, role))
    }
    return adminList
}
fun onlyEmployee(): List<User> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select * from users where "Role" = 'employee';
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val employeeList = mutableListOf<User>()

    while(resultRequestAll.next()) {
        val id = resultRequestAll.getInt(1)
        val firstName = resultRequestAll.getString(2)
        val lastName = resultRequestAll.getString(3)
        val email = resultRequestAll.getString(4)
        val password = resultRequestAll.getString(5)
        val phone = resultRequestAll.getString(6)
        val role = resultRequestAll.getString(7)
        employeeList.add(User(id, firstName, lastName, email, password, phone, role))
    }
    return employeeList
}

data class CountAdminUser(val countUser: Int, val countAdmin: Int)
fun adminUser(): List<CountAdminUser> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select count(*) as count_admin, (
                select count(*) as users from users where "Role" = 'user')
                from users
                where "Role" = 'admin';
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val list = mutableListOf<CountAdminUser>()

    while(resultRequestAll.next()) {
        val admin = resultRequestAll.getInt(1)
        val user = resultRequestAll.getInt(2)
        list.add(CountAdminUser(user, admin))
    }
    return list
}

fun carrierFlight(): List<Flight> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select * from flights
                    where exists(select * from carriers where flights."Carrier_id" = carriers."ID_carrier");
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val list = mutableListOf<Flight>()

    while(resultRequestAll.next()) {
        val flightNumber = resultRequestAll.getInt(1)
        val departure = resultRequestAll.getInt(2)
        val arrival = resultRequestAll.getInt(3)
        val departure_time = resultRequestAll.getString(4)
        val arrival_time = resultRequestAll.getString(5)
        val flight_time = resultRequestAll.getString(6)
        val distance = resultRequestAll.getInt(7)
        val carrier = resultRequestAll.getInt(8)
        val brand = resultRequestAll.getInt(9)
        list.add(Flight(flightNumber, departure, arrival, departure_time, arrival_time, flight_time, distance, carrier, brand))
    }
    return list
}
fun avgTicket(): Float {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select avg("Ticket_price") from tickets where "Departure_date" = current_date;
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    var list: Float = 0F

    while(resultRequestAll.next()) {
        list = resultRequestAll.getFloat(1)
    }
    return list
}

data class TicketPassenger(val number_of_ticket: Int,
                           val flightNumber: Int,
                           val seat_category_code: Int,
                           val id_passanger: Int,
                           val id_brand: Int,
                           val id_carrier: Int,
                           val ticket_price: Float,
                           val distance: Int,
                           val surname: String,
                           val name: String,
                           val passport_series: Int,
                           val passport_id: Int)
fun ticketPassenger(): List<TicketPassenger> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select * from tickets t inner join passengers p on t."Id_passenger" = p."ID_passenger";
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val list = mutableListOf<TicketPassenger>()

    while(resultRequestAll.next()) {
        val number_of_ticket = resultRequestAll.getInt(1)
        val flightNumber = resultRequestAll.getInt(2)
        val seat_category_code = resultRequestAll.getInt(3)
        val id_passanger = resultRequestAll.getInt(4)
        val id_brand = resultRequestAll.getInt(5)
        val id_carrier = resultRequestAll.getInt(6)
        val ticket_price: Float = resultRequestAll.getFloat(7)
        val distance: Int = resultRequestAll.getInt(10)
        val surname: String = resultRequestAll.getString(12)
        val name: String = resultRequestAll.getString(13)
        val passport_series: Int = resultRequestAll.getInt(14)
        val passport_id: Int = resultRequestAll.getInt(15)
        list.add(TicketPassenger(number_of_ticket, flightNumber, seat_category_code, id_passanger, id_brand, id_carrier, ticket_price, distance, surname, name, passport_series, passport_id))
    }
    return list
}

data class TicketBrand(val number_of_ticket: Int, val brand: String)

fun ticketBrand(): List<TicketBrand> {
    Class.forName("org.postgresql.Driver")
    val database = DriverManager.getConnection("jdbc:postgresql://172.20.8.18:5432/kp0091_05", "st0091", "qwerty91")

    val sqlRequestAll = """
                select "Number_of_ticket", "Brand" from tickets inner join aircraftbrands a on a."ID_brand" = tickets."Id_brand" where "Departure_date" = current_date and "Arrival_date" = current_date;
            """.trimIndent()
    val queryRequestAll = database.prepareStatement(sqlRequestAll)
    val resultRequestAll = queryRequestAll.executeQuery()

    val list = mutableListOf<TicketBrand>()

    while(resultRequestAll.next()) {
        val number_of_ticket = resultRequestAll.getInt(1)
        val brand = resultRequestAll.getString(2)
        list.add(TicketBrand(number_of_ticket, brand))
    }
    return list
}

