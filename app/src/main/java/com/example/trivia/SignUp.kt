package com.example.trivia

import android.R.attr.name
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class SignUp : AppCompatActivity() {

    private lateinit var btn_next: Button
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var nameU: EditText
    private lateinit var age: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        ///
        btn_next = findViewById(R.id.btn_registrar)
        nameU = findViewById(R.id.t_name)
        email = findViewById(R.id.t_correo)
        pass = findViewById(R.id.t_contraseña)
        age = findViewById(R.id.t_age)

        btn_next.setOnClickListener {
            val databaseReference =
                FirebaseDatabase.getInstance().reference
            val nuevoUsuario = User(
                nameU.text.toString(),
                email.text.toString(),
                pass.text.toString(),
                age.text.toString().toInt()
            )
            val claveUsuario =
                databaseReference.child("usuarios").push().key
            databaseReference.child("usuarios").child(claveUsuario!!)
                .setValue(nuevoUsuario)

            val intent =
                Intent(this,LogIn::class.java)
            startActivity(intent)
        }

    }
}