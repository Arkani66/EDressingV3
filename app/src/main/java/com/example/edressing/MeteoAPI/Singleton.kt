package com.example.edressing.MeteoAPI

import com.example.meteoguesser.MeteoAPI.MeteoAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by $(USER) on $(DATE).
 */
class Singleton {
    companion object{
        //var cache: Cache = Cache(File(PetApplication.context?.getCacheDir(),"responses"),10 * 1024 * 1024)

        /*var okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()*/

        /*var mkittenApi: MeteoAPI =  Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            //.client(okHttpClient)
            .build()
            .create(MeteoAPI::class.java)*/
        var mmeteoAPI: MeteoAPI =  Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MeteoAPI::class.java)

    }

}