package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class LocationType(val seat_category_code: Int? = null, val place: String, val seat_price_per_kilometers: Float)

object LocationTypes : Table() {
    val seat_category_code: Column<Int> = integer("Seat_category_code").autoIncrement()
    val place: Column<String> = varchar("Place", 20)
    val seat_price_per_kilometers: Column<Float> = float("Seat_price_per_kilometers")

    override val primaryKey = PrimaryKey(seat_category_code)
}