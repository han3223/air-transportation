package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.Passenger
import com.example.database.dataClass.Passengers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOPassengers {
    suspend fun allPassengers(): List<Passenger>
    suspend fun passengers(id_passenger: Int): Passenger?
    suspend fun addNewPassengers(surname: String, name: String, passport_series: Int, passport_id: Int): Passenger?
    suspend fun deletePassengers(id_passenger: Int): Boolean
}


class DAOPassengersImpl : DAOPassengers {
    private fun resultRowToPassenger(row: ResultRow) = Passenger(
        id_passenger = row[Passengers.id_passenger],
        surname = row[Passengers.surname],
        name = row[Passengers.name],
        passport_series = row[Passengers.passport_series],
        passport_id = row[Passengers.id_passenger]
    )

    override suspend fun allPassengers(): List<Passenger> = dbQuery {
        Passengers.selectAll().map(::resultRowToPassenger)
    }

    override suspend fun passengers(id_passenger: Int): Passenger? = dbQuery {
        Passengers
            .select { Passengers.id_passenger eq id_passenger }
            .map(::resultRowToPassenger)
            .singleOrNull()
    }

    override suspend fun addNewPassengers(
        surname: String,
        name: String,
        passport_series: Int,
        passport_id: Int
    ): Passenger? = dbQuery {
        val insertStatement = Passengers.insert {
            it[Passengers.surname] = surname
            it[Passengers.name] = name
            it[Passengers.passport_series] = passport_series
            it[Passengers.passport_id] = passport_id
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToPassenger)
    }

    override suspend fun deletePassengers(id_passenger: Int): Boolean = dbQuery {
        Passengers.deleteWhere { Passengers.id_passenger eq id_passenger } > 0
    }
}