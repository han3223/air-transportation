package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class User(val id: Int? = null, val email: String, val login: String, val password: String)

object Users : Table() {
    val id: Column<Int> = integer("Id_user").autoIncrement()
    val email: Column<String> = varchar("Email", 100)
    val login: Column<String> = varchar("Login", 30)
    val password: Column<String> = varchar("Password", 50)

    override val primaryKey = PrimaryKey(id)
}