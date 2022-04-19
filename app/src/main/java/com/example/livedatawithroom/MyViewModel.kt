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
    var prevEnabledLiveData = MutableLiveData<Boolean>(true)
    var scoreLiveData=MutableLiveData<Int>(0)

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
        prevEnabledLiveData.value=true
        numberLiveData.value.let { number->
            if(questionCount> numberLiveData.value!!){
                questionLiveData.value=questionList[number!!]

            }else{
                nextEnabledLiveData.value=false
            }

        }
    }
    fun chechPrev(){
        numberLiveData.value=numberLiveData.value?.minus(1)
        nextEnabledLiveData.value=true
        numberLiveData.value.let { number->
            if( numberLiveData.value!=0){
                questionLiveData.value=questionList[number!!]


            }else{
                prevEnabledLiveData.value=false

            }

        }
    }

}