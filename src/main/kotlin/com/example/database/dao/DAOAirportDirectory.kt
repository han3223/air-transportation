package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.AirportDirectory
import com.example.database.dataClass.AirportsDirectory
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOAirportDirectory {
    suspend fun allAirportDirectory(): List<AirportDirectory>
    suspend fun airportDirectory(code_airport: Int): AirportDirectory?
    suspend fun addNewAirportDirectory(city: String, airport_name: String): AirportDirectory?
    suspend fun deleteAirportDirectory(code_airport: Int): Boolean
}

class DAOAirportDirectoryImpl : DAOAirportDirectory {
    private fun resultRowToAirportDirectory(row: ResultRow) = AirportDirectory(
        code_airport = row[AirportsDirectory.code_airport],
        city = row[AirportsDirectory.city],
        airport_name = row[AirportsDirectory.airport_name]
    )

    override suspend fun allAirportDirectory(): List<AirportDirectory> = dbQuery{
        AirportsDirectory.selectAll().map(::resultRowToAirportDirectory)
    }

    override suspend fun airportDirectory(code_airport: Int): AirportDirectory? = dbQuery {
        AirportsDirectory
            .select { AirportsDirectory.code_airport eq code_airport }
            .map(::resultRowToAirportDirectory)
            .singleOrNull()
    }

    override suspend fun addNewAirportDirectory(city: String, airport_name: String): AirportDirectory? = dbQuery {
        val insertStatement = AirportsDirectory.insert {
            it[AirportsDirectory.airport_name] = airport_name
            it[AirportsDirectory.city] = city
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToAirportDirectory)
    }

    override suspend fun deleteAirportDirectory(code_airport: Int): Boolean = dbQuery {
        AirportsDirectory.deleteWhere { AirportsDirectory.code_airport eq code_airport } > 0
    }

}