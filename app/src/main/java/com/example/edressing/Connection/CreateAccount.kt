package com.example.edressing.Connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.edressing.R

class CreateAccount : AppCompatActivity() {

    private lateinit var mlogin: EditText
    private lateinit var mmdp: EditText
    private lateinit var mcreationButton: Button

   // val mainViewModel: ConnectionViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mlogin = findViewById(R.id.login_edit)
        mmdp = findViewById(R.id.mdp_edit)
        mcreationButton = findViewById(R.id.Create_account)


    }
}