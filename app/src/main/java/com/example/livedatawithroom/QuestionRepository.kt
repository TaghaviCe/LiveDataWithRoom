package com.example.livedatawithroom

import android.content.Context

object QuestionRepository {
    var db:AppDatabase?=null
    fun initDB(context: Context){
      db= AppDatabase.getAppDataBase(context)
        var questionDao=db?.questionDao()
        questionDao?.insertAll(
            QuestionEntities(1,"q1",30),
            QuestionEntities(2,"q2",40),
            QuestionEntities(3,"q3",69))
    }
//    val questionList = arrayListOf(
//        " result of 2 + 2 " ,
//        " result of  5 - 1 " ,
//        " result of 100 * 21"
//    )
    fun getQuestion(): List<QuestionEntities> {
        return db!!.questionDao().getAll()
    }

}