package com.example.livedatawithroom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {
    @Query("SELECT * FROM QuestionEntities")
    fun getAll():List<QuestionEntities>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg question :QuestionEntities)

    @Query("SELECT count(*) FROM QuestionEntities")
    fun getTotalNumberOfQuestionsLiveData(): LiveData<Int>
    @Query("SELECT count(*) FROM Questionentities")
    fun getTotalNumberOfQuestionsInt():Int

    @Delete
    fun delete(question: QuestionEntities)
}