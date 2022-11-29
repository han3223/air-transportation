package com.example.database.dao

import com.example.database.DatabaseFactory
import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.*
import kotlinx.coroutines.selects.select
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.io.Serializable

interface DAOUser {
    suspend fun allUser(): List<User>
    suspend fun user(email: String, password: String): User?
    suspend fun user(email: String): User?
    suspend fun addNewUser(firstName:String,
                           lastName:String,
                           email: String,
                           password: String,
                           phone: String): User?
    suspend fun deleteUser(id: Int): Boolean
    suspend fun updateRole(email: String, newRole: String): Boolean
}

class DAOUserImpl : DAOUser{
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
        password = row[Users.password],
        phone = row[Users.phone],
        role = row[Users.role]
    )

    override suspend fun updateRole(email: String, newRole: String): Boolean = dbQuery {
        Users.update(where = {Users.email eq email}) {
            it[role] = newRole
        } > 0
    }

    override suspend fun allUser(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }


    override suspend fun user(email: String): User? = dbQuery {
        Users
            .select { (Users.email eq email) }
            .map(::resultRowToUser)
            .singleOrNull()
    }
    override suspend fun user(email: String, password: String): User? = dbQuery {
        Users
            .select { (Users.email eq email) and (Users.password eq password)}
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(firstName:String,
                                    lastName:String,
                                    email: String,
                                    password: String,
                                    phone: String): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.email] = email
            it[Users.password] = password
            it[Users.phone] = phone
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }
}
