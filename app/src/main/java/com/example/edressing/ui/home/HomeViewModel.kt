package com.example.edressing.ui.home

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edressing.Connection.FirebaseUtils
import com.example.edressing.MeteoAPI.MeteoResponse
import com.example.edressing.MeteoAPI.Singleton
import com.example.edressing.R
import com.example.edressing.ui.BDDapi.UserHelper
import com.example.edressing.ui.models.User
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val mmeteoResponse : MutableLiveData<MeteoModel> = MutableLiveData()
    var mcity: MutableLiveData<String> = MutableLiveData()

    init {
        mmeteoResponse.value = MeteoLoader
        getCityFromFirestore()
        callAPICat(mcity)
    }

    private fun getCurrentUser(): FirebaseUser?{
        var user: FirebaseUser? = FirebaseUtils.firebaseAuth.currentUser
        return user
    }

    private fun getCityFromFirestore() {
        var city :String? = "Paris"
        if(getCurrentUser() != null) {
            UserHelper.getUser(getCurrentUser()!!.uid).addOnSuccessListener { document ->
                val currentUser: User? = document.toObject(User::class.java)
                city = if (TextUtils.isEmpty(currentUser?.getCity())) "Dijon" else currentUser?.getCity()
            }
           /*UserHelper.getUser(getCurrentUser()!!.uid).addOnSuccessListener { document ->
                val currentUser: User? = document.toObject(User::class.java)
                val city =
                    if (TextUtils.isEmpty(currentUser?.getCity())) getString(R.string.info_no_usercity_found) else currentUser?.getCity()
            }*/
        }
        mcity = MutableLiveData(city)
    }

    fun callAPICat(city : MutableLiveData<String>){

        mmeteoResponse.value = MeteoLoader
        Singleton.mmeteoAPI.getWeather(city.value.toString(), "399cf91df5aaa7ae4f360f3197d371cc", "metric", "fr").enqueue(object :
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

    fun callAPICat2(city : String){

        mmeteoResponse.value = MeteoLoader
        Singleton.mmeteoAPI.getWeather(city.toString(), "399cf91df5aaa7ae4f360f3197d371cc", "metric", "fr").enqueue(object :
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