package com.example.equis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Configuración de insets para el diseño edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos del layout
        val edtUsuario: EditText = findViewById(R.id.edtUsuario)
        val edtPassword: EditText = findViewById(R.id.edtPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        // Credenciales simuladas
        val usuarioCorrecto = "usuario1"
        val passwordCorrecta = "123456"

        btnLogin.setOnClickListener {
            val usuario = edtUsuario.text.toString()
            val password = edtPassword.text.toString()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (usuario == usuarioCorrecto && password == passwordCorrecta) {
                // Login exitoso: navega a MainActivity
                Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza LoginActivity para evitar regresar con el botón atrás
            } else {
                // Credenciales incorrectas
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
