package com.example.livedatawithroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntities(
    @PrimaryKey (autoGenerate = true) val number:Int,
    var descr:String,
    var answer:Int
)