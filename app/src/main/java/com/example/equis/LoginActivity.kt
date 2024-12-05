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
    private val validUsername = "admin"
    private val validPassword = "123456"

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
        loginButton.isEnabled = false // Deshabilitar botón mientras se procesa el inicio
    }

    private fun hideProgressBar() {
        progressBar.visibility = ProgressBar.GONE
        loginButton.isEnabled = true
    }

    private fun performLogin(username: String, password: String) {
        // Simula un retraso para procesar el inicio de sesión
        Handler().postDelayed({
            hideProgressBar()
            if (username == validUsername && password == validPassword) {
                saveUsername(username) // Guarda el nombre del usuario
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                navigateToMain()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }, 2000) // Simula un proceso de 2 segundos
    }

    private fun saveUsername(username: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username) // Guarda el nombre de usuario
        editor.apply()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Cierra la actividad de inicio de sesión
    }
}
