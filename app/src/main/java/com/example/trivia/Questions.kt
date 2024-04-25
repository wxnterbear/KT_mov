package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.nio.BufferUnderflowException
import kotlin.random.Random

class Questions : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    //
    private lateinit var nPregunta: TextView
    private lateinit var textPregunta: TextView
    private lateinit var res1: TextView
    private lateinit var res2: TextView
    private lateinit var res3: TextView
    private lateinit var res4: TextView



    private val mapa: MutableList<Pregunta> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        nPregunta = findViewById(R.id.txt_npregunta)
        textPregunta = findViewById(R.id.txt_pregunta)
        //
        res1 = findViewById(R.id.txt_res1)
        res2 = findViewById(R.id.txt_res2)
        res3 = findViewById(R.id.txt_res3)
        res4 = findViewById(R.id.txt_res4)

        res1.isClickable = true
        res2.isClickable = true
        res3.isClickable = true
        res4.isClickable = true


        obtenerDatosDeFirebase()
        pintarPregunta()

    }


    fun obtenerDatosDeFirebase() {
        val referenciaPreguntas = database.child("preguntas")
        var i = 0
        referenciaPreguntas.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (preguntaSnapshot in dataSnapshot.children) {
                    val pregunta = preguntaSnapshot.child("pregunta").getValue(String::class.java)
                    val respuestas: List<String?> = preguntaSnapshot.child("respuestas").children.map {
                        it.getValue(String::class.java)
                    }
                    val respuestaCorrecta: String? = preguntaSnapshot.child("respuestaCorrecta").getValue(String::class.java)
                    val Pregunta =  Pregunta(pregunta,respuestaCorrecta, respuestas)
                    mapa[i] = Pregunta
                    i++

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error al leer datos de Firebase: ${databaseError.message}")
            }
        })
    }
    fun pintarPregunta(){
        var pts = 0
        var eleccion = ""
        //
        for (i in 1..4) {
        var ran = Random.nextInt(1, mapa.size)
        val Pregunta = mapa[ran]
        mapa.remove(Pregunta)
        val respuestaCorrecta = Pregunta.respuestaCorrecta
        val respuestas = Pregunta.respuestas

        nPregunta.text = "Pregunta Numero: $i"
        //
        res1.text = respuestas[0]
        res2.text = respuestas[1]
        res3.text = respuestas[2]
        res4.text = respuestas[3]

        res1.setOnClickListener {
            run {
                eleccion = res1.text.toString()
            }
        }
        res2.setOnClickListener {
            run {
                eleccion = res2.text.toString()
            }
        }
        res3.setOnClickListener {
            run {
                eleccion = res3.text.toString()
            }
        }
        res4.setOnClickListener {
            run {
                eleccion = res3.text.toString()
            }
        }
        if (eleccion == respuestaCorrecta){
            pts++

            }
        }
        nextActivity(pts)

    }

    private fun nextActivity(pts: Int){
        val intent = Intent(this@Questions, Score::class.java)
        intent.putExtra("Score", pts)
        startActivity(intent)
    }
}