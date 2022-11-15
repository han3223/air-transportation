package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

data class Flight(
    val flight_number: Int? = null,
    val point_of_departure: Int,
    val point_of_arrival: Int,
    val departure_time: LocalDateTime,
    val arrival_time: LocalDateTime
)

object Flights : Table() {
    val flight_number: Column<Int> = integer("Flight_number").autoIncrement()
    val point_of_departure: Column<Int> = integer("Point_of_departure").references(AirportsDirectory.code_airport)
    val point_of_arrival: Column<Int> = integer("Point_of_arrival")references(AirportsDirectory.code_airport)
    val departure_time: Column<LocalDateTime> = datetime("Departure_time")
    val arrival_time: Column<LocalDateTime> = datetime("Arrival_time")

    override val primaryKey = PrimaryKey(flight_number)
}