package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class AircraftBrand(val id: Int? = null, val brand: String)

object AircraftBrands : Table() {
    val id: Column<Int> = integer("ID_brand").autoIncrement()
    val brand: Column<String> = varchar("Brand", 30)

    override val primaryKey = PrimaryKey(id)
}