package com.example.livedatawithroom

import androidx.room.*

@Dao
interface QuestionDao {
    @Query("SELECT * FROM QuestionEntities")
    fun getAll():List<QuestionEntities>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg question :QuestionEntities)

    @Delete
    fun delete(question: QuestionEntities)
}