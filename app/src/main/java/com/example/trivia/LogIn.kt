package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class LogIn : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var btn_next: Button
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var txt_registro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        btn_next = findViewById(R.id.btn_ingresar)
        email = findViewById(R.id.Etext_correo)
        pass = findViewById(R.id.Etxt_pass)
        txt_registro = findViewById(R.id.txt_registro)
        //

        database = Firebase.database.reference

        txt_registro.setOnClickListener {
            run {
                val intent: Intent = Intent(this@LogIn, SignUp::class.java)
                startActivity(intent)
            }
        }

        btn_next.setOnClickListener {
            run {
                comprobrar()
            }
        }
    }
    fun comprobrar(){
        val referenciaUsuarios = database.child("usuarios")
        referenciaUsuarios.orderByChild("email").equalTo(email.text.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (usuarioSnapshot in dataSnapshot.children) {
                        val password = usuarioSnapshot.child("pass").getValue(
                            String::class.java
                        )

                        if (password != null && password == pass.text.toString()) {
                            val nombre = usuarioSnapshot.child("name").getValue(
                                String::class.java
                            )
                            val intent: Intent = Intent(this@LogIn, Questions::class.java)
                            intent.putExtra("name", nombre)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@LogIn,
                                "USUARIO O CONTRASEÑA INCORRECTA",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this@LogIn,
                        "USUARIO O CONTRASEÑA INCORRECTA",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar errores de base de datos, si es necesario
            }
        })



    }
}