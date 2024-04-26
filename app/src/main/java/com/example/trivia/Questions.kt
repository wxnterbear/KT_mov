package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
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
    private var eleccion = ""
    private var pts = 0
    private var nVeces = 0
    private var respuestaCorrecta:  String? = null
    private val mapa: MutableList<Pregunta> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        //

        //
        nPregunta = findViewById(R.id.txt_npregunta)
        textPregunta = findViewById(R.id.txt_pregunta)
        //
        res1 = findViewById(R.id.txt_res1)
        res2 = findViewById(R.id.txt_res2)
        res3 = findViewById(R.id.txt_res3)
        res4 = findViewById(R.id.txt_res4)
        //
        res1.setOnClickListener {
            run {
                eleccion = res1.text.toString()

                if (eleccion == respuestaCorrecta){
                    pts += 1
                }

                if (nVeces < 4) {
                    pintarPregunta()
                }else{
                    nextActivity()
                }
            }
        }
        res2.setOnClickListener {
            run {
                eleccion = res2.text.toString()
                if (eleccion == respuestaCorrecta){
                    pts += 1
                }
                if (nVeces < 4) {
                    pintarPregunta()
                }else{

                    nextActivity()
                }

            }
        }
        res3.setOnClickListener {
            run {
                eleccion = res3.text.toString()
                if (eleccion == respuestaCorrecta){
                    pts += 1
                }
                if (nVeces < 4) {
                    pintarPregunta()
                }else{
                    nextActivity()
                }

            }
        }
        res4.setOnClickListener {
            run {
                eleccion = res4.text.toString()

                if (eleccion == respuestaCorrecta){
                    pts += 1
                }

                if (nVeces < 4) {
                    pintarPregunta()
                }else{
                    nextActivity()
                }

            }
        }

        //
        database = Firebase.database.reference



        obtenerDatosDeFirebase()


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
                    mapa.add(Pregunta)
                }
                pintarPregunta()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error al leer datos de Firebase: ${databaseError.message}")
            }
        })
    }
    fun pintarPregunta(){
            nVeces++
            var ran = Random.nextInt(0, mapa.size)
            val Pregunta = mapa[ran]
            mapa.remove(Pregunta)
            //
            respuestaCorrecta = Pregunta.respuestaCorrecta
            val respuestas = Pregunta.respuestas
            textPregunta.text = Pregunta.pregunta
            nPregunta.text = "Pregunta Numero: ${nVeces}"
            //
            res1.text = respuestas[0]
            res2.text = respuestas[1]
            res3.text = respuestas[2]
            res4.text = respuestas[3]


    }
    private fun nextActivity(){
        val intentN = intent
        val intent = Intent(this@Questions, Score::class.java)
        intent.putExtra("name", intentN.getStringExtra("name"))
        intent.putExtra("Score", pts)
        startActivity(intent)


    }
}