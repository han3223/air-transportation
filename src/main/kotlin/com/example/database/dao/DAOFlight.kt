package com.example.database.dao


import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.Flight
import com.example.database.dataClass.Flights
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOFlight {
    suspend fun allFlight(): List<Flight>
    suspend fun flight(point_of_departure: Int, point_of_arrival: Int): List<Flight>
    suspend fun addNewFlight(point_of_departure: Int,
                             point_of_arrival: Int,
                             departure_time: String,
                             arrival_time: String,
                             distance: Int,
                             carrier_id: Int,
                             brand_id: Int): Flight?
    suspend fun deleteFlight(flight_number: Int): Boolean
    suspend fun flightNum(flight_number: Int): List<Flight>
}

class DAOFlightImpl : DAOFlight {
    private fun resultRowToFlight(row: ResultRow) = Flight(
        flight_number = row[Flights.flight_number],
        point_of_departure = row[Flights.point_of_departure],
        point_of_arrival = row[Flights.point_of_arrival],
        departure_time = row[Flights.departure_time],
        arrival_time = row[Flights.arrival_time],
        carrier_id = row[Flights.carrier_id],
        distance = row[Flights.distance],
        brand_id = row[Flights.brand_id]
    )

    override suspend fun allFlight(): List<Flight> = dbQuery {
        Flights.selectAll().map(::resultRowToFlight)
    }

    override suspend fun flight(point_of_departure: Int, point_of_arrival: Int): List<Flight> = dbQuery {
        Flights
            .select { (Flights.point_of_departure eq point_of_departure) and (Flights.point_of_arrival eq point_of_arrival) }
            .map(::resultRowToFlight)
    }

    override suspend fun flightNum(flight_number: Int): List<Flight> = dbQuery {
        Flights.select { Flights.flight_number eq flight_number }.map(::resultRowToFlight)
    }

    override suspend fun addNewFlight(
        point_of_departure: Int,
        point_of_arrival: Int,
        departure_time: String,
        arrival_time: String,
        distance: Int,
        carrier_id: Int,
        brand_id: Int
    ): Flight? = dbQuery {
        val insertStatement = Flights.insert {
            it[Flights.point_of_departure] = point_of_departure
            it[Flights.point_of_arrival] = point_of_arrival
            it[Flights.departure_time] = departure_time
            it[Flights.arrival_time] = arrival_time
            it[Flights.distance] = distance
            it[Flights.carrier_id] = carrier_id
            it[Flights.brand_id] = brand_id
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToFlight)
    }

    override suspend fun deleteFlight(flight_number: Int): Boolean = dbQuery {
        Flights.deleteWhere { Flights.flight_number eq flight_number } > 0
    }

}