package com.example.edressing.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.edressing.Connection.CreateAccount
import com.example.edressing.Connection.FirebaseUtils.firebaseAuth
import com.example.edressing.R
import com.example.edressing.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var mbutton: Button
    private lateinit var mtext: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       /* notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)*/

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        /*notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mbutton = view.findViewById(R.id.button_signed_out)
        mtext = view.findViewById(R.id.text_notifications)

        mbutton.setOnClickListener {
            firebaseAuth.signOut()
            //startActivity(Intent(this, CreateAccount::class.java))
            //toast("signed out")
            /*Toast.makeText(this, "Déconnecté !", Toast.LENGTH_LONG)
                .show()
            onDestroyView()*/
            mtext.text = "Déconnecté !"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}