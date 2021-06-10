package com.example.edressing.ui.Armoire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.edressing.R
import com.example.edressing.databinding.FragmentArmoireBinding

class ArmoireFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentArmoireBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentArmoireBinding.inflate(inflater, container, false)
        val root: View = binding.root

       // val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
           // textView.text = it
        })
        return root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}