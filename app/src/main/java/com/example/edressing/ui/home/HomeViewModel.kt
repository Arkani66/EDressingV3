package com.example.edressing.ui.home

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meteoguesser.MeteoAPI.MeteoResponse
import com.example.meteoguesser.MeteoAPI.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val mmeteoResponse : MutableLiveData<MeteoModel> = MutableLiveData()
    init {
        callAPICat()
    }

    private fun callAPICat(){
        mmeteoResponse.value = MeteoLoader
        Singleton.mmeteoAPI.getWeather(/*"Paris", "399cf91df5aaa7ae4f360f3197d371cc", "metric", "fr"*/).enqueue(object :
            Callback<MeteoResponse> {
            override fun onResponse(
                call: Call<MeteoResponse>,
                response: Response<MeteoResponse>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                   // Toast.makeText(this@MainActivity, "Success !", Toast.LENGTH_LONG).show()
                    val mmeteoresponse: MeteoResponse? = response.body()
                    if(mmeteoresponse != null) {
                        mmeteoResponse.value = MeteoSuccess(mmeteoresponse)
                    }
                    else{
                        mmeteoResponse.value = MeteoFailure
                    }

                }
            }

            override fun onFailure(call: Call<MeteoResponse?>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "Failed !", Toast.LENGTH_LONG).show()
               mmeteoResponse.value = MeteoFailure
            }
        })

    }
}