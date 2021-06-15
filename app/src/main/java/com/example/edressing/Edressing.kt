package com.example.edressing

import android.app.Application
import android.content.Context

/**
 *Created by $(USER) on $(DATE).
 */
class Edressing : Application() {

    companion object{
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}