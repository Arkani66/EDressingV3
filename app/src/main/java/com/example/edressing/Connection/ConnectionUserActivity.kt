package com.example.edressing.Connection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.edressing.BottomNaviguation
import com.example.edressing.Connection.Extensions.toast
import com.example.edressing.Connection.FirebaseUtils.firebaseAuth
import com.example.edressing.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class ConnectionUserActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>

    private lateinit var mbutton_newmember: Button
    private lateinit var mbuttonconnec: Button
    private lateinit var mbackgroundimage: ImageView
    private lateinit var mlogin: EditText
    private lateinit var mmdp: EditText
    private lateinit var mimage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_user)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mbuttonconnec = findViewById(R.id.button_connec)
        mbutton_newmember = findViewById(R.id.button_newmember)
        mbackgroundimage = findViewById(R.id.background_connection)
        mlogin = findViewById(R.id.login)
        mmdp = findViewById(R.id.mdp)
        mimage = findViewById(R.id.imageView)

        signInInputsArray = arrayOf(mlogin, mmdp)
        Glide.with(this)
            .load(R.drawable.wallpaper)
            .centerCrop()
            .into(mbackgroundimage)
        Glide.with(this)
            .load(R.drawable.ladie)
            .into(mimage)


        mbuttonconnec.setOnClickListener{
            /*Toast.makeText(this, R.string.message_accueil_btn, Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, BottomNaviguation::class.java)
             startActivity(intent)*/
            signInUser()
        }

        mbutton_newmember.setOnClickListener {
           Toast.makeText(this, R.string.message_newmember_btn, Toast.LENGTH_LONG)
                .show()
            /*val intent = Intent(this, BottomNaviguation::class.java)
            startActivity(intent)*/
            startActivity(Intent(this, CreateAccount::class.java))
           // toast("you're going to create an account")
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, BottomNaviguation::class.java))
            toast("Bon retour sur l'EDressing")
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = mlogin.text.toString().trim()
        signInPassword = mmdp.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, BottomNaviguation::class.java))
                        toast("La Connection est un succès !")
                        finish()
                    } else {
                        toast("Le mot de passe ou le login sont incorrects !")
                    }
                }
        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        }
    }

}