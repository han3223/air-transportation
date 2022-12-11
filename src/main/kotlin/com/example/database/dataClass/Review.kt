package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class Review(val id: Int, val first_name: String, val last_name: String, val theme: String, val text: String)

object Reviews: Table() {
    val id: Column<Int> = integer("Id_rewiews").autoIncrement()
    val first_name: Column<String> = varchar("First_name", 30)
    val last_name: Column<String> = varchar("Last_name", 40)
    val theme: Column<String> = varchar("Theme", 60)
    val text: Column<String> = text("Text")

    override val primaryKey = PrimaryKey(id)
}