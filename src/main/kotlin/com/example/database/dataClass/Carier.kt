package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Carrier(val id: Int? = null, val company_name: String)

object Carriers : Table() {
    val id: Column<Int> = integer("ID_carrier").autoIncrement()
    val company_name: Column<String> = varchar("Company_name", 30)

    override val primaryKey = PrimaryKey(id)

//    fun toCarrier(row: ResultRow): Carrier =
//        Carrier (
//            id = row[Carriers.id],
//            company_name = row[Carriers.company_name]
//        )
}