package com.example.livedatawithroom

import android.content.Context
import androidx.lifecycle.LiveData

object QuestionRepository {
    var db:AppDatabase?=null
    fun initDB(context: Context){
      db= AppDatabase.getAppDataBase(context)
        var questionDao=db?.questionDao()
        questionDao?.insertAll(
            QuestionEntities(1,"2+3",5),
            QuestionEntities(2,"4+5",9),
            QuestionEntities(3,"6+7",13))
    }
//    val questionList = arrayListOf(
//        " result of 2 + 2 " ,
//        " result of  5 - 1 " ,
//        " result of 100 * 21"
//    )
    fun getQuestion(): List<QuestionEntities> {
        return db!!.questionDao().getAll()
    }
    fun questionsCountInt():Int{
        return db!!.questionDao().getTotalNumberOfQuestionsInt()
    }

    fun questionsCountLivedata(): LiveData<Int> {
        return db!!.questionDao().getTotalNumberOfQuestionsLiveData()
    }

}