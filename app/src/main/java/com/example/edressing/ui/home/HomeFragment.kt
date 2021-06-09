package com.example.edressing.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.edressing.R
import com.example.edressing.databinding.FragmentHomeBinding
import com.example.meteoguesser.MeteoAPI.MeteoResponse
import com.example.meteoguesser.MeteoAPI.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var mtextcity: TextView
    private lateinit var mtexttemperature: TextView
    private lateinit var mtextdescription: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)*/

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        /*homeViewModel.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mtextcity = view.findViewById(R.id.weather_city)
        mtexttemperature = view.findViewById(R.id.weather_temperature)
        mtextdescription = view.findViewById(R.id.weather_description)


        homeViewModel.mmeteoResponse.observe(viewLifecycleOwner, Observer {mmeteoModel ->
            //mloader.isVisible = mmeteoModel is MeteoLoader
            //mtexterror.isVisible = mmeteoModel is MeteoFailure
            if(mmeteoModel is MeteoSuccess) {
                mtextcity.text = mmeteoModel.meteo.name
                mtexttemperature.text = mmeteoModel.meteo.main.temp.toString()
                mtextdescription.text = mmeteoModel.meteo.weather[0].description
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
/*
    fun getMeteo() : String{


        Singleton.mmeteoAPI.getWeather(/*"Paris", "399cf91df5aaa7ae4f360f3197d371cc", "metric", "fr"*/).enqueue(object :
            Callback<MeteoResponse> {
            override fun onResponse(
                call: Call<MeteoResponse>,
                response: Response<MeteoResponse>
            ) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(this@MainActivity, "Success !", Toast.LENGTH_LONG).show()
                    val mmeteoresponse: MeteoResponse? = response.body()
                    if(mmeteoresponse != null) {
                        textcity.text = mmeteoresponse.name
                        val description: String = mmeteoresponse.weather[0].description
                        textdescription.text = description
                        texttemperature.text = mmeteoresponse.main.temp.toString()
                        textplus.text = "plus"
                    }

                }
            }

            override fun onFailure(call: Call<MeteoResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed !", Toast.LENGTH_LONG).show()
                textplus.text = call.request().url().toString()
                val erreur = t.message
                textdescription.text = erreur
            }
        })
        return "null"
    }*/
}