package com.example.database.dataClass

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

data class UserLoginPassword(val email: String, val password: String, val role: String)

data class UserEmail(val email: String)
data class User(val id: Int? = null, val email: String, val password: String,val phone: String, val role: String)

object Users : Table() {
    val id: Column<Int> = integer("Id_user").autoIncrement()
    val email: Column<String> = varchar("Email", 100)
    val password: Column<String> = varchar("Password", 50)
    val phone: Column<String> = varchar("Phone", 12)
    val role: Column<String> = varchar("Role", 20).default("user")

    override val primaryKey = PrimaryKey(id)
}