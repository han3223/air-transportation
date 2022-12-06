package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.AircraftBrand
import com.example.database.dataClass.AircraftBrands
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOAircraftBrand {
    suspend fun allAircraftBrand(): List<AircraftBrand>
    suspend fun aircraftBrand(id: Int): AircraftBrand?
    suspend fun addNewAircraftBrand(name: String, costFactor: Double): AircraftBrand?
    suspend fun deleteAircraftBrand(id: Int): Boolean
}

class DAOAircraftBrandImpl : DAOAircraftBrand {
    private fun resultRowToAircraftBrand(row: ResultRow) = AircraftBrand(
        id = row[AircraftBrands.id],
        brand = row[AircraftBrands.brand],
        cost_factor = row[AircraftBrands.cost_factor]
    )

    override suspend fun allAircraftBrand(): List<AircraftBrand> = dbQuery {
        AircraftBrands.selectAll().map(::resultRowToAircraftBrand)
    }

    override suspend fun aircraftBrand(id: Int): AircraftBrand? = dbQuery {
        AircraftBrands
            .select {AircraftBrands.id eq id}
            .map(::resultRowToAircraftBrand)
            .singleOrNull()
    }

    override suspend fun addNewAircraftBrand(name: String, costFactor: Double): AircraftBrand? = dbQuery {
        val insertStatement = AircraftBrands.insert {
            it[brand] = name
            it[cost_factor] = costFactor

        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToAircraftBrand)
    }

    override suspend fun deleteAircraftBrand(id: Int): Boolean = dbQuery {
        AircraftBrands.deleteWhere { AircraftBrands.id eq id } > 0
    }
}