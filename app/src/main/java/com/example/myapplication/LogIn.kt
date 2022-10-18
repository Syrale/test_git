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

class LogIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = Firebase.auth

        val registerText: TextView = findViewById(R.id.textView_login)

        registerText.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        // verify input from user
        val email: EditText = findViewById(R.id.editText_LoginEmail)
        val password: EditText = findViewById(R.id.editText_LoginPassword)

        if(email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill the necessary fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val loginEmail = email.text.toString()
        val loginPassword = password.text.toString()

        auth.signInWithEmailAndPassword(loginEmail,loginPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
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