package com.example.database.dao
import com.example.database.dataClass.Carrier
import com.example.database.dataClass.Carriers
import com.example.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOCarrier {
    suspend fun allCarriers(): List<Carrier>
    suspend fun carrier(id: Int): Carrier?
    suspend fun addNewCarrier(name: String): Carrier?
    suspend fun editCarrier(id: Int, name: String): Boolean
    suspend fun deleteCarrier(id: Int): Boolean
}

class DAOCarrierImpl : DAOCarrier {
    private fun resultRowToCarrier(row: ResultRow) = Carrier(
        id = row[Carriers.id],
        company_name = row[Carriers.company_name]
    )

    override suspend fun allCarriers(): List<Carrier> = dbQuery {
        Carriers.selectAll().map(::resultRowToCarrier)
    }

    override suspend fun carrier(id: Int): Carrier? = dbQuery {
        Carriers
            .select { Carriers.id eq id}
            .map(::resultRowToCarrier)
            .singleOrNull()
    }

    override suspend fun addNewCarrier(name: String): Carrier? = dbQuery {
        val insertStatement = Carriers.insert {
            it[company_name] = name
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCarrier)
    }

    override suspend fun editCarrier(id: Int, name: String): Boolean = dbQuery {
        Carriers.update({ Carriers.id eq id}) {
            it[company_name] = name
        } > 0
    }

    override suspend fun deleteCarrier(id: Int): Boolean = dbQuery {
        Carriers.deleteWhere { Carriers.id eq id } > 0
    }

}