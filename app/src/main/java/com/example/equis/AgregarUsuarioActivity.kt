package com.example.equis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AgregarUsuarioActivity : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var editTxtContrasena: EditText
    private lateinit var switchCliente: Switch
    private lateinit var switchAdmin: Switch
    private lateinit var buttonRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuario)

        // Inicialización de los elementos del formulario
        editTextNombre = findViewById(R.id.editTxtNombre)
        editTextTelefono = findViewById(R.id.editTxtTelefono)
        editTextCorreo = findViewById(R.id.editTxtEmail)
        editTxtContrasena = findViewById(R.id.editTxtContrasena)
        switchCliente = findViewById(R.id.switchCliente)
        switchAdmin = findViewById(R.id.switchAdmin)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)

        // Evitar que ambos switches se activen al mismo tiempo
        switchCliente.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchAdmin.isChecked = false
            }
        }

        switchAdmin.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                switchCliente.isChecked = false
            }
        }

        // Acción al hacer clic en el botón de registrar
        buttonRegistrar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val telefono = editTextTelefono.text.toString()
            val correo = editTextCorreo.text.toString()
            val contrasena = editTxtContrasena.text.toString()
            val tipoUsuario = if (switchCliente.isChecked) "cliente" else "administrador"

            // Validación de los campos
            if (nombre.isEmpty() || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Guardamos los datos en SharedPreferences
                val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", nombre)
                editor.putString("telefono", telefono)
                editor.putString("correo", correo)
                editor.putString("userType", tipoUsuario)
                editor.putString("password", contrasena) // Guardar la contraseña
                editor.apply()

                // Redirigir a la pantalla principal
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
