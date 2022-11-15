package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.User
import com.example.database.dataClass.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOUser {
    suspend fun allUser(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(email: String, login: String, password: String): User?
    suspend fun deleteUser(id: Int): Boolean
}

class DAOUserImpl : DAOUser{
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        email = row[Users.email],
        login = row[Users.login],
        password = row[Users.password]
    )

    override suspend fun allUser(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun user(id: Int): User? = dbQuery {
        Users
            .select { Users.id eq id}
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(email: String, login: String, password: String): User? = dbQuery {
        val insertStatement = Users.insert {
            it[Users.email] = email
            it[Users.login] = login
            it[Users.password] = password
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun deleteUser(id: Int): Boolean = dbQuery {
        Users.deleteWhere { Users.id eq id } > 0
    }
}