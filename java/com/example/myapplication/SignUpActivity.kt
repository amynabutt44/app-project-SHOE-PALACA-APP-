package com.example.myapplication

import SignUpManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpManager: SignUpManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val emailEditText = findViewById<EditText>(R.id.signup_email)
        val passwordEditText = findViewById<EditText>(R.id.signup_password)
        val signUpButton = findViewById<Button>(R.id.signup_button)
        val login_button = findViewById<TextView>(R.id.login_link)
        signUpManager = SignUpManager(RetrofitInstance.api)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signUpManager.signUp(email, password) { success, message ->
                    if (success) {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        // Navigate to another screen if needed
                    } else {
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }


        login_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
