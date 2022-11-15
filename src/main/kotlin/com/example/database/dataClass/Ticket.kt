package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

data class Ticket(val number_of_ticket: Int? = null,
                  val flight_number: Int,
                  val seat_category_code: Int,
                  val id_passenger: Int,
                  val id_brand: Int,
                  val id_carrier: Int,
                  val ticket_price: Float,
                  val departure_date: LocalDate,
                  val arrival_date: LocalDate,
                  val distance: Int)

object Tickets : Table() {
    val number_of_ticket: Column<Int> = integer("Number_of_ticket").autoIncrement()
    val flight_number: Column<Int> = integer("Flight_number").references(Flights.flight_number)
    val seat_category_code: Column<Int> = integer("Seat_category_code").references(LocationTypes.seat_category_code)
    val id_passenger: Column<Int> = integer("Id_passenger").references(Passengers.id_passenger)
    val id_brand: Column<Int> = integer("Id_brand").references(AircraftBrands.id)
    val id_carrier: Column<Int> = integer("Id_carrier").references(Carriers.id)
    val ticket_price: Column<Float> = float("Ticket_price")
    val departure_date: Column<LocalDate> = date("Departure_date")
    val arrival_date: Column<LocalDate> = date("Arrival_date")
    val distance: Column<Int> = integer("Distance")

    override val primaryKey = PrimaryKey(number_of_ticket)

}