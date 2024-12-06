package com.example.equis

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    // Credenciales predefinidas
    private val users = mapOf(
        "admin" to "123",
        "cliente" to "123"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.edtUsuario)
        passwordEditText = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInput(username, password)) {
                showProgressBar()
                performLogin(username, password)
            }
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            usernameEditText.error = "El usuario es obligatorio"
            return false
        }
        if (password.isEmpty()) {
            passwordEditText.error = "La contraseña es obligatoria"
            return false
        }
        return true
    }

    private fun showProgressBar() {
        progressBar.visibility = ProgressBar.VISIBLE
        loginButton.isEnabled = false
    }

    private fun hideProgressBar() {
        progressBar.visibility = ProgressBar.GONE
        loginButton.isEnabled = true
    }

    private fun performLogin(username: String, password: String) {
        Handler().postDelayed({
            hideProgressBar()
            if (users[username] == password) {
                saveUserDetails(username)
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                navigateToMain()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }, 1000)
    }

    private fun saveUserDetails(username: String) {
        val userType = if (username == "admin") "admin" else "cliente"
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("userType", userType)
        editor.apply()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
