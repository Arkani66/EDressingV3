package com.example.edressing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.edressing.Connection.ConnectionUserActivity
import com.example.edressing.Connection.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var mbackgroundimage: ImageView
    private lateinit var mladieimage: ImageView

    val mainViewModel: MainViewModel by inject()

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
            .into(mladieimage)

        mbackgroundimage.setOnClickListener {
            val intent = Intent(this, ConnectionUserActivity::class.java)
            startActivity(intent)
        }
    }
}