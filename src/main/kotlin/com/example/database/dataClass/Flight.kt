package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

data class Flight(
    val flight_number: Int? = null,
    val point_of_departure: Int,
    val point_of_arrival: Int,
    val departure_time: String,
    val arrival_time: String,
    val distance: Int,
    val carrier_id: Int,
    val brand_id: Int

)

object Flights : Table() {
    val flight_number: Column<Int> = integer("Flight_number").autoIncrement()
    val point_of_departure: Column<Int> = integer("Point_of_departure").references(AirportsDirectory.code_airport)
    val point_of_arrival: Column<Int> = integer("Point_of_arrival")references(AirportsDirectory.code_airport)
    val departure_time: Column<String> = varchar("Departure_time", 10)
    val arrival_time: Column<String> = varchar("Arrival_time", 10)
    val distance: Column<Int> = integer("Distance")
    val carrier_id: Column<Int> = integer("Carrier_id").references(Carriers.id)
    val brand_id: Column<Int> = integer("Brand_id").references(AircraftBrands.id)

    override val primaryKey = PrimaryKey(flight_number)
}