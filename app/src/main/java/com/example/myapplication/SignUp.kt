package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val loginText: TextView = findViewById(R.id.textView_login)
        loginText.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            performSignUp()
        }
    }

    private fun performSignUp() {
        val email = findViewById<EditText>(R.id.editText_RegisterEmail)
        val password = findViewById<EditText>(R.id.editText_RegisterPassword)

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill the necessary fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, moves to the next activity i.e. MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Sign up successful.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }

    }
}