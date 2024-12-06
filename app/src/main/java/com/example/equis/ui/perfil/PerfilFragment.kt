package com.example.equis.ui.perfil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.equis.AgregarUsuarioActivity
import com.example.equis.R
class PerfilFragment : Fragment() {

    private lateinit var txtUsuario: TextView
    private lateinit var txtNumTelefonico: TextView
    private lateinit var txtEmail: TextView
    private lateinit var btnAgregarUsuario: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        txtUsuario = view.findViewById(R.id.txtUsuario)
        txtNumTelefonico = view.findViewById(R.id.txtNumTelefonico)
        txtEmail = view.findViewById(R.id.txtEmail)
        btnAgregarUsuario = view.findViewById(R.id.btnAgregarUsuario)

        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Usuario desconocido")
        val telefono = sharedPreferences.getString("telefono", "Sin número")
        val correo = sharedPreferences.getString("correo", "Sin correo")
        val userType = sharedPreferences.getString("userType", "cliente")

        txtUsuario.text = username
        txtNumTelefonico.text = telefono
        txtEmail.text = correo

        // Ocultar botón si el usuario es cliente
        if (userType == "cliente") {
            btnAgregarUsuario.visibility = View.GONE
        } else {
            btnAgregarUsuario.setOnClickListener {
                // Al hacer clic en el botón, abrir la actividad AgregarUsuarioActivity
                val intent = Intent(requireContext(), AgregarUsuarioActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }
}

