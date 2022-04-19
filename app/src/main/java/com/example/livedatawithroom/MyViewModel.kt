package com.example.livedatawithroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MyViewModel(app:Application):AndroidViewModel(app) {


    val numberLiveData=MutableLiveData<Int>(0)
  //  val questionLiveData=MutableLiveData<String>(QuestionRepository.questionList[0])
   // private lateinit var questionCount:Int
    var questionCount:Int
    var questionAnswer= MutableLiveData<Int>(0)
    lateinit var questionList:List<QuestionEntities>
    var currentquestionLiveData=MutableLiveData<QuestionEntities>()
    var nextEnabledLiveData = MutableLiveData<Boolean>(true)
    var prevEnabledLiveData = MutableLiveData<Boolean>(true)
    var scoreLiveData=MutableLiveData<Int>(0)

    init {
        QuestionRepository.initDB(app.applicationContext)
        questionList=QuestionRepository.getQuestion()
        currentquestionLiveData.value=questionList[0]
        questionCount=questionList.size
        questionAnswer.value=questionList[0].answer

        //
    }
    fun check() {
      //
        numberLiveData.value=numberLiveData.value?.plus(1)
        prevEnabledLiveData.value=true
        numberLiveData.value.let { number->
            if(questionCount> numberLiveData.value!!){
                currentquestionLiveData.value=questionList[number!!]
                questionAnswer.value=questionList[number!!].answer

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
                currentquestionLiveData.value=questionList[number!!]
                questionAnswer.value=questionList[number!!].answer


            }else{
                prevEnabledLiveData.value=false

            }

        }
    }
    fun chechAnswer(number:String){
         var num=number.toInt()
        if(num==questionAnswer.value){
            scoreLiveData.value=scoreLiveData.value?.plus(5)
        }else{
            scoreLiveData.value=scoreLiveData.value?.minus(2)
        }

    }

}