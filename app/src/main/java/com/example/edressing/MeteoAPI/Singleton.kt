package com.example.edressing.MeteoAPI

import com.example.edressing.Edressing
import com.example.meteoguesser.MeteoAPI.MeteoAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 *Created by $(USER) on $(DATE).
 */
class Singleton {
    companion object{
        var cache: Cache = Cache(File(Edressing.context?.getCacheDir(),"responses"),10 * 1024 * 1024)

        var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        var mmeteoAPI: MeteoAPI =  Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MeteoAPI::class.java)

    }

}