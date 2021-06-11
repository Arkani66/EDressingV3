package com.example.edressing.ui.Armoire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.edressing.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Ensembles : Fragment() {

    private lateinit var mimage: ImageView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ensembles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mimage = view.findViewById(R.id.imageViewensembles)
        Glide.with(this)
            .load(R.drawable.robe)
            .centerCrop()
            .into(mimage)
    }
}