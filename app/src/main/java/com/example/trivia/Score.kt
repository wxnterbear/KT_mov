package com.example.trivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Score : AppCompatActivity() {

    private lateinit var txt_score: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val intentScore = intent
        intentScore.getIntExtra("Score", -1)


    }
}