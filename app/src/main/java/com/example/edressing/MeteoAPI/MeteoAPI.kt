package com.example.meteoguesser.MeteoAPI

import com.example.edressing.MeteoAPI.MeteoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *Created by $(USER) on $(DATE).
 */
interface MeteoAPI {
    @GET("weather?"/*q=Paris&appid=399cf91df5aaa7ae4f360f3197d371cc&units=metric&lang=fr"*/)
    fun getWeather(@Query("q")  city : String, @Query("appid") api_key: String, @Query("units") units : String, @Query("lang") langue : String)  : Call<MeteoResponse>
}