package com.example.database.dao


import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.Flight
import com.example.database.dataClass.Flights
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDateTime

interface DAOFlight {
    suspend fun allFlight(): List<Flight>
    suspend fun flight(flight_number: Int): Flight?
    suspend fun addNewFlight(point_of_departure: Int, point_of_arrival: Int, departure_time: LocalDateTime, arrival_time: LocalDateTime): Flight?
    suspend fun deleteFlight(flight_number: Int): Boolean
}

class DAOFlightImpl : DAOFlight {
    private fun resultRowToFlight(row: ResultRow) = Flight(
        flight_number = row[Flights.flight_number],
        point_of_departure = row[Flights.point_of_departure],
        point_of_arrival = row[Flights.point_of_arrival],
        departure_time = row[Flights.departure_time],
        arrival_time = row[Flights.arrival_time],

    )

    override suspend fun allFlight(): List<Flight> = dbQuery {
        Flights.selectAll().map(::resultRowToFlight)
    }

    override suspend fun flight(flight_number: Int): Flight? = dbQuery {
        Flights
            .select { Flights.flight_number eq flight_number }
            .map(::resultRowToFlight)
            .singleOrNull()
    }

    override suspend fun addNewFlight(
        point_of_departure: Int,
        point_of_arrival: Int,
        departure_time: LocalDateTime,
        arrival_time: LocalDateTime
    ): Flight? = dbQuery {
        val insertStatement = Flights.insert {
            it[Flights.point_of_departure] = point_of_departure
            it[Flights.point_of_arrival] = point_of_arrival
            it[Flights.departure_time] = departure_time
            it[Flights.arrival_time] = arrival_time
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToFlight)
    }

    override suspend fun deleteFlight(flight_number: Int): Boolean = dbQuery {
        Flights.deleteWhere { Flights.flight_number eq flight_number } > 0
    }

}