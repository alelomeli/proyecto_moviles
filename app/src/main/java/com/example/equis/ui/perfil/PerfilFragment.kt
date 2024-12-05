package com.example.equis.ui.perfil

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.equis.R

class PerfilFragment : Fragment() {

    private lateinit var txtUsuario: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        txtUsuario = view.findViewById(R.id.txtUsuario)

        // Recupera el nombre del usuario desde SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Usuario desconocido")
        txtUsuario.text = username // Establece el nombre en el TextView

        return view
    }
}
