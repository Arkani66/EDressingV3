package com.example.edressing.Connection

import android.app.Activity
import android.widget.Toast

/**
 *Created by $(USER) on $(DATE).
 */
object Extensions {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}