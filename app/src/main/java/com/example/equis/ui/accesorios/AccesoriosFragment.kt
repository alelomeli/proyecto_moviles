package com.example.equis.ui.accesorios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.equis.R
import com.example.equis.databinding.FragmentClasesBinding
import com.example.equis.ui.gallery.GalleryViewModel

class AccesoriosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_accesorios,container, false)

        return root
    }

}