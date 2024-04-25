package com.example.trivia

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Pregunta(val pregunta: String?, var respuestaCorrecta: String?, var respuestas: List<String?>) {
}