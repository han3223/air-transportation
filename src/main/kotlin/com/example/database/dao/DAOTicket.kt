package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.Ticket
import com.example.database.dataClass.Tickets
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDate

interface DAOTicket {
    suspend fun allTicket(): List<Ticket>
    suspend fun ticket(number_of_ticket: Int): Ticket?
    suspend fun addNewTicket(flight_number: Int,
                             seat_category_code: Int,
                             id_passenger: Int,
                             id_brand: Int,
                             id_carrier: Int,
                             ticket_price: Float,
                             departure_date: LocalDate,
                             arrival_date: LocalDate,
                             distance: Int): Ticket?
    suspend fun deleteTicket(number_of_ticket: Int): Boolean
}

class DAOTicketImpl : DAOTicket {
    private fun resultRowToTicket(row: ResultRow) = Ticket(
        number_of_ticket = row[Tickets.number_of_ticket],
        flight_number = row[Tickets.flight_number],
        seat_category_code = row[Tickets.seat_category_code],
        id_passenger = row[Tickets.id_passenger],
        id_brand = row[Tickets.id_brand],
        id_carrier = row[Tickets.id_carrier],
        ticket_price = row[Tickets.ticket_price],
        departure_date = row[Tickets.departure_date],
        arrival_date = row[Tickets.arrival_date],
        distance = row[Tickets.distance]
    )

    override suspend fun allTicket(): List<Ticket> = dbQuery {
        Tickets.selectAll().map(::resultRowToTicket)
    }

    override suspend fun ticket(number_of_ticket: Int): Ticket? = dbQuery {
        Tickets
            .select { Tickets.number_of_ticket eq number_of_ticket }
            .map(::resultRowToTicket)
            .singleOrNull()
    }

    override suspend fun addNewTicket(
        flight_number: Int,
        seat_category_code: Int,
        id_passenger: Int,
        id_brand: Int,
        id_carrier: Int,
        ticket_price: Float,
        departure_date: LocalDate,
        arrival_date: LocalDate,
        distance: Int
    ): Ticket? = dbQuery {
        val insertStatement = Tickets.insert {
            it[Tickets.flight_number] = flight_number
            it[Tickets.seat_category_code] = seat_category_code
            it[Tickets.id_passenger] = id_passenger
            it[Tickets.id_brand] = id_brand
            it[Tickets.id_carrier] = id_carrier
            it[Tickets.ticket_price] = ticket_price
            it[Tickets.departure_date] = departure_date
            it[Tickets.arrival_date] = arrival_date
            it[Tickets.distance] = distance
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTicket)
    }

    override suspend fun deleteTicket(number_of_ticket: Int): Boolean = dbQuery {
        Tickets.deleteWhere { Tickets.number_of_ticket eq number_of_ticket } > 0
    }
}