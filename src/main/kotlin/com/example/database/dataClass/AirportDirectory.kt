package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class AirportDirectory(val code_airport: Int? = null, val city: String, val airport_name: String)

object AirportsDirectory : Table() {
    val code_airport: Column<Int> = integer("Code_airport").autoIncrement()
    val city: Column<String> = varchar("City", 30)
    val airport_name: Column<String> = varchar("Airport_name", 50)

    override val primaryKey = PrimaryKey(code_airport)
}