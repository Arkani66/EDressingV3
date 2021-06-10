package com.example.edressing.ui.Armoire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.edressing.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Liste : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.veste).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_toVestes)
        }
        view.findViewById<Button>(R.id.bas).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_toBas)
        }
        view.findViewById<Button>(R.id.haut).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_toHauts)
        }
        view.findViewById<Button>(R.id.chaussure).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_toChaussures)
        }
        view.findViewById<Button>(R.id.ensemble).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_toEnsembles)
        }
        view.findViewById<Button>(R.id.dessous).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_toDessous)
        }
    }
}