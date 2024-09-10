package com.example.myapplication

import LoginManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.login_email)
        val passwordEditText = findViewById<EditText>(R.id.login_password)
        val loginButton = findViewById<Button>(R.id.login_button)
        val signUpLink = findViewById<TextView>(R.id.sign_up_link)

        val apiService = RetrofitInstance.api
        loginManager = LoginManager(apiService)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginManager.login(email, password) { success ->
                if (success) {
                    // Navigate to the home page
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()  // Close the LoginActivity
                } else {
                    Toast.makeText(this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signUpLink.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
