package com.example.livedatawithroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(app:Application):AndroidViewModel(app) {


    val numberLiveData=MutableLiveData<Int>(0)
  //  val questionLiveData=MutableLiveData<String>(QuestionRepository.questionList[0])
   // private lateinit var questionCount:Int
  var questionCount:Int
    lateinit var questionList:List<QuestionEntities>
    var questionLiveData=MutableLiveData<QuestionEntities>()
    var nextEnabledLiveData = MutableLiveData<Boolean>(true)

    init {
        QuestionRepository.initDB(app.applicationContext)
        questionList=QuestionRepository.getQuestion()
        questionLiveData.value=questionList[0]
        questionCount=questionList.size

        //
    }
    fun check() {
      //
        numberLiveData.value=numberLiveData.value?.plus(1)
        numberLiveData.value.let { number->
            if(questionCount> numberLiveData.value!!){
                questionLiveData.value=questionList[number!!]

            }else{
                nextEnabledLiveData.value=false
            }

        }
    }

}