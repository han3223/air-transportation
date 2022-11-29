package com.example.database

import com.example.database.dataClass.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    val database = Database.connect(
        url = "jdbc:postgresql://172.20.8.18:5432/kp0091_05", driver = "org.postgresql.Driver",
        user = "st0091", password = "qwerty91"
    )
    fun init() {
        transaction {
            SchemaUtils.create(Carriers)
            SchemaUtils.create(AircraftBrands)
            SchemaUtils.create(AirportsDirectory)
            SchemaUtils.create(Flights)
            SchemaUtils.create(LocationTypes)
            SchemaUtils.create(Passengers)
            SchemaUtils.create(Tickets)
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}