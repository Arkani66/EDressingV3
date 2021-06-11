package com.example.edressing.Connection

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.edressing.BottomNaviguation
import com.example.edressing.R

class ConnectionUserActivity : AppCompatActivity() {

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

        mbuttonconnec.setOnClickListener{
            Toast.makeText(this, R.string.message_accueil_btn, Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, BottomNaviguation::class.java)
             startActivity(intent)
        }

        mbutton_newmember.setOnClickListener {
            Toast.makeText(this, R.string.message_newmember_btn, Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, BottomNaviguation::class.java)
            startActivity(intent)
        }
    }

}