package com.example.edressing.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edressing.R
import com.example.edressing.databinding.FragmentHomeBinding
import com.example.edressing.ui.home.Clothes.Clothes
import com.example.edressing.ui.home.Clothes.ClothesAdaptater
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var mrecyclerview : RecyclerView
    private val madapterView = ClothesAdaptater(listOf(), ::onClickedClothes)
    private val mlayoutmanager = LinearLayoutManager(context)

    private lateinit var mtextcity: TextView
    private lateinit var mtexttemperature: TextView
    private lateinit var mtextdescription: TextView
    private lateinit var mtexterror: TextView
    private lateinit var mloader: ProgressBar
    private lateinit var mimage: ImageView

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

        mrecyclerview = view.findViewById(R.id.recycler_view)
        mrecyclerview.apply {
            layoutManager = this@HomeFragment.mlayoutmanager
            adapter = this@HomeFragment.madapterView
        }


        mtextcity = view.findViewById(R.id.weather_city)
        mtexttemperature = view.findViewById(R.id.weather_temperature)
        mtextdescription = view.findViewById(R.id.weather_description)
        mloader = view.findViewById(R.id.progressbar)
        mimage = view.findViewById(R.id.weather_image)
        mtexterror = view.findViewById(R.id.texterror)

        var url_image = "http://openweathermap.org/img/wn/10n@2x.png"

        homeViewModel.mmeteoResponse.observe(viewLifecycleOwner, Observer {mmeteoModel ->
            mloader.isVisible = mmeteoModel is MeteoLoader
            mtexterror.isVisible = mmeteoModel is MeteoFailure
            if(mmeteoModel is MeteoSuccess) {
                mtextcity.text = mmeteoModel.meteo.name
                mtexttemperature.text = mmeteoModel.meteo.main.temp.toString()
                mtextdescription.text = mmeteoModel.meteo.weather[0].description
                url_image = "http://openweathermap.org/img/wn/"+mmeteoModel.meteo.weather[0].icon+"@2x.png"
                Glide.with(this)
                    .load(url_image)
                    .centerCrop()
                    .into(mimage)
                //madapterView.SortClothes(mmeteoModel.meteo.main.temp,mmeteoModel.meteo.main.feels_like,mmeteoModel.meteo.main.humidity, mlistClothe)

            }
        })
        val mlistClothe: ArrayList<Clothes> = arrayListOf<Clothes>().apply {
            add(Clothes("T-Shirt","1","chaud","soleil"))
            add((Clothes("Manteau","2","froid","pluie")))
            add(Clothes("Jupe","3","chaud","soleil"))
            add(Clothes("Pantalon","4","froid","pluie"))
            add(Clothes("Jupe","5","chaud","soleil"))
        }
        madapterView.updateList(mlistClothe)

    }

    private fun onClickedClothes(clothe: Clothes) {

    }

    //private fun SortClothes

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
