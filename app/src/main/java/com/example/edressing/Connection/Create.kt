package com.example.edressing.Connection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.edressing.Connection.entity.User
import com.example.edressing.R
import org.koin.android.ext.android.inject

class Create : AppCompatActivity() {

    private lateinit var mlogin: EditText
    private lateinit var mmdp: EditText
    private lateinit var mcreationButton: Button
    val mainViewModel: MainViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creation_compte)

        mcreationButton = findViewById(R.id.Create_account)
        mlogin = findViewById(R.id.loginAccount_edit)
        mmdp = findViewById(R.id.passwordAccount_edit)

        mcreationButton.setOnClickListener(){
            val monIntentRetour =  Intent(this,ConnectionUserActivity::class.java)
            val user = User((mlogin.toString().trim()),mmdp.toString())
            if(user.email !="" && user.password != "") {
                mainViewModel.onClickedLoginAccount(user)
                startActivity(monIntentRetour)
            }else{
                Toast.makeText(applicationContext,"add a password or a user",Toast.LENGTH_SHORT).show()
            }
        }
    }
}