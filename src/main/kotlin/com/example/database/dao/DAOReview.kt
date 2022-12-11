package com.example.database.dao

import com.example.database.DatabaseFactory
import com.example.database.dataClass.Review
import com.example.database.dataClass.Reviews
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


interface DAOReview {
    suspend fun allReview(): List<Review>
    suspend fun review(theme: String): Review?
    suspend fun addNewReview(firstName: String, lastName: String, theme: String, text: String): Review?
    suspend fun deleteReview(id: Int): Boolean
}
class DAOReviewImpl : DAOReview {
    private fun resultRowToReview(row: ResultRow) = Review(
        id = row[Reviews.id],
        first_name = row[Reviews.first_name],
        last_name = row[Reviews.last_name],
        theme = row[Reviews.theme],
        text = row[Reviews.text]
    )

    override suspend fun allReview(): List<Review> = DatabaseFactory.dbQuery {
        Reviews.selectAll().map(::resultRowToReview)
    }

    override suspend fun review(theme: String): Review? = DatabaseFactory.dbQuery {
        Reviews
            .select { ( Reviews.theme eq theme) }
            .map(::resultRowToReview)
            .singleOrNull()
    }

    override suspend fun addNewReview(firstName: String,
                                      lastName: String,
                                      theme: String,
                                      text: String): Review? = DatabaseFactory.dbQuery {
        val insertStatement = Reviews.insert {
            it[Reviews.first_name] = firstName
            it[Reviews.last_name] = lastName
            it[Reviews.theme] = theme
            it[Reviews.text] = text
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToReview)
    }

    override suspend fun deleteReview(id: Int): Boolean = DatabaseFactory.dbQuery {
        Reviews.deleteWhere { Reviews.id eq id } > 0
    }
}