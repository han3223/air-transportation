package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.User
import com.example.database.dataClass.UserLoginPassword
import com.example.database.dataClass.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

interface DAOUser {
    suspend fun allUser(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(email: String, password: String, phone: String): User?
    suspend fun deleteUser(id: Int): Boolean
    suspend fun getLoginAndPasswordUser(login: String, password: String): String
}

class DAOUserImpl : DAOUser{
    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        email = row[Users.email],
        password = row[Users.password],
        phone = row[Users.phone],
        role = row[Users.role]
    )

    private fun getLogPass(row: ResultRow) = UserLoginPassword(
        email = row[Users.email], password = row[Users.password], role = row[Users.role]
    )

    override suspend fun getLoginAndPasswordUser(login: String, password: String): String {
        for (i in 0..mapUserLoginPassword().lastIndex) {
            if (mapUserLoginPassword()[i] == UserLoginPassword(login, password, "user")) {
                println("user")
                return "user"
            }
            else if (mapUserLoginPassword()[i] == UserLoginPassword(login, password, "admin")) {
                println("admin")
                return "admin"
            }
            else if (mapUserLoginPassword()[i] == UserLoginPassword(login, password, "employee")) {
                println("employee")
                return "employee"
            }
        }
        return "null"
    }
    private suspend fun mapUserLoginPassword(): List<UserLoginPassword> = dbQuery {
        Users.selectAll().map(::getLogPass)
    }

    override suspend fun allUser(): List<User> = dbQuery {
        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun user(id: Int): User? = dbQuery {
        Users
            .select { Users.id eq id}
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun addNewUser(email: String, password: String, phone: String): User? = dbQuery {
        val insertStatement = Users.insert {
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