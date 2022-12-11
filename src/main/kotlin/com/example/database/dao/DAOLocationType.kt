package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.LocationType
import com.example.database.dataClass.LocationTypes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOLocationType {
    suspend fun allLocationType(): List<LocationType>
    suspend fun locationType(seat_category_code: Int): LocationType?
    suspend fun addNewLocationType(place: String, seat_price_per_kilometers: Float): LocationType?
    suspend fun deleteLocationType(seat_category_code: Int): Boolean
    suspend fun locationType(place: String): LocationType?
    suspend fun updateLocationType(place: String, price: Float): Boolean
}

class DAOLocationTypeImpl : DAOLocationType {
    private fun resultRowLocationType(row: ResultRow) = LocationType(
        seat_category_code = row[LocationTypes.seat_category_code],
        place = row[LocationTypes.place],
        seat_price_per_kilometers = row[LocationTypes.seat_price_per_kilometers]
    )

    override suspend fun allLocationType(): List<LocationType> = dbQuery {
        LocationTypes.selectAll().map(::resultRowLocationType)
    }

    override suspend fun locationType(seat_category_code: Int): LocationType? = dbQuery {
        LocationTypes
            .select { LocationTypes.seat_category_code eq seat_category_code }
            .map(::resultRowLocationType)
            .singleOrNull()
    }

    override suspend fun locationType(place: String): LocationType? = dbQuery {
        LocationTypes
            .select { LocationTypes.place eq place }
            .map(::resultRowLocationType)
            .singleOrNull()
    }

    override suspend fun addNewLocationType(place: String, seat_price_per_kilometers: Float): LocationType? = dbQuery {
        val insertStatement = LocationTypes.insert {
            it[LocationTypes.place] = place
            it[LocationTypes.seat_price_per_kilometers] = seat_price_per_kilometers
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowLocationType)
    }

    override suspend fun updateLocationType(place: String, price: Float): Boolean = dbQuery {
        LocationTypes.update({ LocationTypes.place eq place}) {
            it[seat_price_per_kilometers] = price
        } > 0
    }

    override suspend fun deleteLocationType(seat_category_code: Int): Boolean = dbQuery {
        LocationTypes.deleteWhere { LocationTypes.seat_category_code eq seat_category_code } > 0
    }

}