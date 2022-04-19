package com.example.livedatawithroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    val viewModel:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        var textView = findViewById<TextView>(R.id.tvNumber)
        var buttonNext = findViewById<Button>(R.id.buttonNext)
        var buttonPre=findViewById<Button>(R.id.buttonPre)
        var buttonSend=findViewById<Button>(R.id.buttonSend)
        var answer=findViewById<EditText>(R.id.answer)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        var questionText = findViewById<TextView>(R.id.tvQuestion)


        buttonNext.setOnClickListener {
            viewModel.check()
        }
        buttonPre.setOnClickListener {
            viewModel.chechPrev()
        }

        val numberObserver=Observer<Int>{ number->
             textView.text=number.toString()
             progressBar.progress=number
        }
        val questionObserver= Observer<QuestionEntities> {
            questionText.text=it.descr
        }
        val buttonEnabledObserver = Observer<Boolean>{  enabled ->
            buttonNext.isEnabled = enabled
        }
        val buttonEnabledObserverPrev= Observer<Boolean> { enable->
            buttonPre.isEnabled=enable
        }

        viewModel.numberLiveData.observe(this,numberObserver)
        viewModel.questionLiveData.observe(this,questionObserver)
        viewModel.nextEnabledLiveData.observe(this,buttonEnabledObserver)
        viewModel.prevEnabledLiveData.observe(this,buttonEnabledObserverPrev)


    }
}