package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Passenger(val id_passenger: Int? = null,
                     val surname: String,
                     val name: String,
                     val passport_series: Int,
                     val passport_id: Int)

object Passengers : Table() {
    val id_passenger: Column<Int> = integer("ID_passenger").autoIncrement()
    val surname: Column<String> = varchar("Surname", 30)
    val name: Column<String> = varchar("Name", 60)
    val passport_series: Column<Int> = integer("Passport_series")
    val passport_id: Column<Int> = integer("Passport_id")

    override val primaryKey = PrimaryKey(id_passenger)
}