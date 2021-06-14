package com.example.edressing.Connection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.edressing.BottomNaviguation
import com.example.edressing.Connection.Extensions.toast
import com.example.edressing.Connection.FirebaseUtils.firebaseAuth
import com.example.edressing.Connection.FirebaseUtils.firebaseUser
import com.example.edressing.R
import com.google.firebase.auth.FirebaseUser

class CreateAccount : AppCompatActivity() {

    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>

    private lateinit var mlogin: EditText
    private lateinit var mmdp: EditText
    private lateinit var mmdp_confirm: EditText
    private lateinit var mcreationButton: Button
    private lateinit var mbackimage: ImageView

   // val mainViewModel: ConnectionViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        mlogin = findViewById(R.id.login_edit)
        mmdp = findViewById(R.id.mdp_edit)
        mmdp_confirm = findViewById(R.id.mdp_edit_confirmation)
        mcreationButton = findViewById(R.id.Create_account)
        mbackimage = findViewById(R.id.background_create_account)
        Glide.with(this)
            .load(R.drawable.wallpaper)
            .centerCrop()
            .into(mbackimage)

        createAccountInputsArray = arrayOf(mlogin, mmdp, mmdp_confirm)

        mcreationButton.setOnClickListener {
            signIn()
        }

    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, BottomNaviguation::class.java))
            toast("welcome back")
        }
    }

    private fun notEmpty(): Boolean = mlogin.text.toString().trim().isNotEmpty() &&
            mmdp.text.toString().trim().isNotEmpty() &&
            mmdp_confirm.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            mmdp.text.toString().trim() == mmdp_confirm.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast("passwords are not matching !")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = mlogin.text.toString().trim()
            userPassword = mmdp.text.toString().trim()

            /*create a user*/
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("created account successfully !")
                        sendEmailVerification()
                        startActivity(Intent(this, BottomNaviguation::class.java))
                        finish()
                    } else {
                        toast("failed to Authenticate !")
                    }
                }
        }
    }

    /* send verification email to the new user. This will only
    *  work if the firebase user is not null.
    */

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }
        }
    }
}