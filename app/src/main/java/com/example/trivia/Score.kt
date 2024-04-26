package com.example.trivia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Score : AppCompatActivity() {

    private lateinit var txt_score: TextView
    private lateinit var txt_name: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val intentScore = intent
        txt_score = findViewById(R.id.txt_score)
        txt_name = findViewById(R.id.txt_name)


        txt_score.text = "Tu puntuacion es: ${intentScore.getIntExtra("Score", -1)}"
        txt_name.text = "Felicitacione: ${intentScore.getStringExtra("name")}"
        


    }
}