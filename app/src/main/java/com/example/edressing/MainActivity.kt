package com.example.edressing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var mbackgroundimage: ImageView
    private lateinit var mladieimage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mbackgroundimage = findViewById(R.id.main_background)
        mladieimage = findViewById(R.id.main_ladie)

        Glide.with(this)
            .load(R.drawable.wallpaper)
            .centerCrop()
            .into(mbackgroundimage)
        Glide.with(this)
            .load(R.drawable.ladie)
            .centerCrop()
            .into(mladieimage)

        mbackgroundimage.setOnClickListener {
            Toast.makeText(this@MainActivity, R.string.message_accueil_btn, Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this, BottomNaviguation::class.java)
            startActivity(intent)
        }
    }
}