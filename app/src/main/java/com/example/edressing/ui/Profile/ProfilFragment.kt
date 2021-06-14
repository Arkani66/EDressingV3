package com.example.edressing.ui.Profile

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.edressing.Connection.FirebaseUtils.firebaseAuth
import com.example.edressing.R
import com.example.edressing.databinding.FragmentProfilBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseUser


class ProfilFragment : Fragment() {

    private lateinit var mProfilViewModel: ProfilViewModel
    private var _binding: FragmentProfilBinding? = null
    private lateinit var mbuttondelete: Button
    private lateinit var mbuttonupdate: Button
    private lateinit var mbuttondeco: Button

    private lateinit var mtextusername: TextView
    private lateinit var mtextusermail: TextView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       /* notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)*/

        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProfilname
        /*notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mbuttondeco = view.findViewById(R.id.button_signed_out)
        mbuttondelete = view.findViewById(R.id.button_delete)
        mbuttonupdate = view.findViewById(R.id.button_update)
        mtextusername = view.findViewById(R.id.text_profilname)
        mtextusermail = view.findViewById(R.id.text_profilemail)

        updateUIWhenCreating()

        mbuttondeco.setOnClickListener {
            onClickSignOutButton()
        }

        mbuttonupdate.setOnClickListener{
            onClickUpdateButton()
        }

        mbuttondelete.setOnClickListener{
            onClickDeleteButton()
        }
    }

    private fun updateUIWhenCreating() {
        if (getCurrentUser() != null) {

            //Get picture URL from Firebase
            /*if (getCurrentUser()!!.photoUrl != null) {
                Glide.with(this)
                    .load(getCurrentUser()!!.photoUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageViewProfile)
            }*/

            //Get email & username from Firebase
            val email =
                if (TextUtils.isEmpty(getCurrentUser()!!.email)) getString(R.string.info_no_email_found)
                else getCurrentUser()!!.email!!
            val username =
                if (TextUtils.isEmpty(getCurrentUser()!!.displayName)) getString(R.string.info_no_username_found)
                else getCurrentUser()!!.displayName!!

            //Update views with data
            mtextusername.text = "Nom d'utilisateur : $username"
            mtextusermail.text = "Email : $email"
        }
    }

    protected fun isCurrentUserLogged(): Boolean? {
        return this.getCurrentUser() != null
    }

    private fun getCurrentUser(): FirebaseUser?{
        var user: FirebaseUser? = firebaseAuth.currentUser
        return user
    }

    fun deleteUserFromFirebase() {
        if (getCurrentUser() != null) {
            val user = firebaseAuth.currentUser!!
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, R.string.msg_deleted_account.toString())
                    }
                }

        }
    }

    private fun onClickDeleteButton() {
       AlertDialog.Builder(activity)
            .setMessage(R.string.popup_message_confirmation_delete_account)
            .setPositiveButton(R.string.popup_message_choice_yes,
                DialogInterface.OnClickListener { dialogInterface, i -> deleteUserFromFirebase() })
            .setNegativeButton(R.string.popup_message_choice_no, null)
            .show()
    }

    private fun onClickUpdateButton() {
        TODO("Not yet implemented")
    }

    private fun onClickSignOutButton() {
        firebaseAuth.signOut()
        //startActivity(Intent(this, CreateAccount::class.java))
        //toast("signed out")
        /*Toast.makeText(this, "Déconnecté !", Toast.LENGTH_LONG)
            .show()
        onDestroyView()*/
        mtextusername.text = "Déconnecté !"
        mtextusermail.text = " "
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}