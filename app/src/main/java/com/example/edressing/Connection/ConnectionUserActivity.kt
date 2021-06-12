package com.example.edressing.Connection

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.edressing.BottomNaviguation
import com.example.edressing.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject

class ConnectionUserActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by inject()

    private lateinit var mbutton_newmember: Button
    private lateinit var mbuttonconnec: Button
    private lateinit var mbackgroundimage: ImageView
    private lateinit var mlogin: EditText
    private lateinit var mmdp: EditText
    private lateinit var mimage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_user)

        mbuttonconnec = findViewById(R.id.button_connec)
        mbutton_newmember = findViewById(R.id.button_newmember)
        mbackgroundimage = findViewById(R.id.background_connection)
        mlogin = findViewById(R.id.login)
        mmdp = findViewById(R.id.mdp)
        mimage = findViewById(R.id.imageView)

        Glide.with(this)
            .load(R.drawable.wallpaper)
            .centerCrop()
            .into(mbackgroundimage)
        Glide.with(this)
            .load(R.drawable.ladie)
            .into(mimage)


        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Connection")
                        .setMessage("Compte existant")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                        val monIntent : Intent =  Intent(this, BottomNaviguation::class.java)
                    startActivity(monIntent)

                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Veuillez créer un compte")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })
        mbuttonconnec.setOnClickListener{
            /*Toast.makeText(this, R.string.message_accueil_btn, Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, BottomNaviguation::class.java)
             startActivity(intent)*/
            mainViewModel.onClickedLogin(mlogin.toString().trim(), mmdp.toString())
        }

        mbutton_newmember.setOnClickListener {
           /* Toast.makeText(this, R.string.message_newmember_btn, Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, BottomNaviguation::class.java)
            startActivity(intent)*/
            MaterialAlertDialogBuilder(this)
                .setTitle("Connection")
                .setMessage("Compte existant")
                .setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
            val monIntent : Intent =  Intent(this,Create::class.java)
            startActivity(monIntent)
        }
    }

}