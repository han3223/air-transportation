package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.database.dataClass.User
import com.example.database.dataClass.UserLoginPassword
import com.example.database.dataClass.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.io.Serializable

interface DAOUser {
    suspend fun allUser(): List<User>
    suspend fun user(id: Int): User?
    suspend fun addNewUser(firstName:String,
                           lastName:String,
                           email: String,
                           password: String,
                           phone: String): User?
    suspend fun deleteUser(id: Int): Boolean
    suspend fun getLoginAndPasswordUser(login: String, password: String): Array<String>
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

    private fun getLogPass(row: ResultRow) = UserLoginPassword(
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
        password = row[Users.password],
        role = row[Users.role]
    )

    override suspend fun getLoginAndPasswordUser(login: String, password: String): Array<String> {
        for (i in 0..mapUserLoginPassword().lastIndex) {
            if (mapUserLoginPassword()[i].email == login && mapUserLoginPassword()[i].password == password) {
                val arrNameRole: Array<String> = arrayOf(
                    mapUserLoginPassword()[i].firstName,
                    mapUserLoginPassword()[i].lastName,
                    mapUserLoginPassword()[i].role)
                when(mapUserLoginPassword()[i].role) {
                    "user" -> return arrNameRole
                    "admin" -> return arrNameRole
                    "employee" -> return arrNameRole
                }
            }
        }
        return emptyArray()
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