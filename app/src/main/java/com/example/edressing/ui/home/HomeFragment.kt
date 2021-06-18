package com.example.edressing.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.edressing.Connection.FirebaseUtils
import com.example.edressing.MeteoAPI.MeteoResponse
import com.example.edressing.MeteoAPI.Singleton
import com.example.edressing.R
import com.example.edressing.databinding.FragmentHomeBinding
import com.example.edressing.ui.BDDapi.UserHelper
import com.example.edressing.ui.home.Clothes.Clothes
import com.example.edressing.ui.home.Clothes.ClothesAdaptater
import com.example.edressing.ui.models.User
import com.example.edressing.ui.models.Vetements
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val COLLECTION_NAME = "vetements"
    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    private lateinit var mrecyclerview : RecyclerView
    private val madapterView = ClothesAdaptater(listOf(), ::onClickedClothes)
    private val mlayoutmanager = LinearLayoutManager(context)
    private var mcity: MutableLiveData<String> = MutableLiveData()

    private val db = Firebase.firestore
    var mlistClothe: ArrayList<Clothes> = recupListClothes()

    private lateinit var mtextcity: TextView
    private lateinit var mtexttemperature: TextView
    private lateinit var mtextdescription: TextView
    private lateinit var mtexterror: TextView
    private lateinit var mloader: ProgressBar
    private lateinit var mimage: ImageView
    private lateinit var mquerycity: EditText
    private lateinit var mbutton: Button
    private lateinit var mbuttontenue: Button

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
        addClothes()
        //val textView: TextView = binding.textHome
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
        mquerycity = view.findViewById(R.id.text_query_city)
        mbutton = view.findViewById(R.id.button_home)
        mbuttontenue = view.findViewById(R.id.button_home_clothes)

        updateUIWhenCreating()

        var url_image : String //= "http://openweathermap.org/img/wn/10n@2x.png"

        homeViewModel.mmeteoResponse.observe(viewLifecycleOwner, Observer {mmeteoModel ->
            mloader.isVisible = mmeteoModel is MeteoLoader
            mtexterror.isVisible = mmeteoModel is MeteoFailure
            mbutton.isVisible = mmeteoModel is MeteoFailure
            mquerycity.isVisible = mmeteoModel is MeteoFailure
            if(mmeteoModel is MeteoSuccess) {
                mtextcity.text = mmeteoModel.meteo.name
                mtexttemperature.text = mmeteoModel.meteo.main.temp.toString()+"°C"
                mtextdescription.text = mmeteoModel.meteo.weather[0].description
                url_image = "http://openweathermap.org/img/wn/"+mmeteoModel.meteo.weather[0].icon+"@2x.png"
                Glide.with(this)
                    .load(url_image)
                    .centerCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .into(mimage)
                //madapterView.SortClothes(mmeteoModel.meteo.main.temp,mmeteoModel.meteo.main.feels_like,mmeteoModel.meteo.main.humidity, mlistClothe)

            }
        })
        /*val mlistClothe: ArrayList<Clothes> = arrayListOf<Clothes>().apply {
            add(Clothes("T-Shirt","1","chaud","soleil"))
            add((Clothes("Manteau","2","froid","pluie")))
            add(Clothes("Jupe","3","chaud","soleil"))
            add(Clothes("Pantalon","4","froid","pluie"))
            add(Clothes("Jupe","5","chaud","soleil"))
        }*/

        mbutton.setOnClickListener{
            if( mquerycity.text.toString() != null) homeViewModel.callAPICat2(mquerycity.text.toString())
            else Toast.makeText(activity, R.string.attention_city_home, Toast.LENGTH_LONG)
                .show()
        }

        mbuttontenue.setOnClickListener{
            madapterView.updateList(mlistClothe)
        }

    }

    private fun recupListClothes(): ArrayList<Clothes> {
        var mlistClothe: ArrayList<Clothes> = arrayListOf<Clothes>()
        db.collection(COLLECTION_NAME)
            .whereEqualTo("temperature", "chaud")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val clothe: Vetements? = document.toObject(Vetements::class.java)
                    mlistClothe.add(Clothes(clothe!!.type,"0",clothe.temperature,clothe.temps))
                //Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                //Log.w(TAG, "Error getting documents: ", exception)
            }
        return mlistClothe
    }

    /* private fun getCityFromFirestore(): String? {
         var city :String? = "Paris"
         if(getCurrentUser() != null) {
             UserHelper.getUser(getCurrentUser()!!.uid).addOnSuccessListener { document ->
                 val currentUser: User? = document.toObject(User::class.java)
                 val city = if (TextUtils.isEmpty(currentUser?.getCity())) Toast.makeText(activity, R.string.info_no_usercity_found, Toast.LENGTH_LONG)
                     .show()
                 else city = currentUser?.getCity()
             }
         }
         return city
     }*/

    private fun updateUIWhenCreating() {
        if (getCurrentUser() != null) {
            //city = UserHelper
            UserHelper.getUser(getCurrentUser()!!.uid).addOnSuccessListener { document ->
                val currentUser: User? = document.toObject(User::class.java)
                val city =
                    if (TextUtils.isEmpty(currentUser?.getCity())) getString(R.string.info_no_usercity_found) else currentUser?.getCity()
                mtextcity.text = city
                if(city!=null) callAPICat(city)
            }
        }
    }

    fun callAPICat(city : String){

        homeViewModel.mmeteoResponse.value = MeteoLoader
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
                        homeViewModel.mmeteoResponse.value = MeteoSuccess(mmeteoresponse)
                    }
                    else{
                        homeViewModel.mmeteoResponse.value = MeteoFailure
                    }

                }
            }

            override fun onFailure(call: Call<MeteoResponse?>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "Failed !", Toast.LENGTH_LONG).show()
                homeViewModel.mmeteoResponse.value = MeteoFailure
            }
        })

    }

    private fun getCurrentUser(): FirebaseUser?{
        var user: FirebaseUser? = FirebaseUtils.firebaseAuth.currentUser
        return user
    }

    private fun addClothes(){
        val cities = db.collection(COLLECTION_NAME)

        val data1 = hashMapOf(
            "couleur" to "bleu",
            "matiere" to "jean",
            "motif" to 1,
            "sorti" to 1,
            "style" to "mignon",
            "temperature" to "froid",
            "temps" to "ensoleille",
            "type" to "Pantalon",
            "vetement" to "bas"
        )
        cities.document("01").set(data1)

        val data2 = hashMapOf(
            "couleur" to "pistache",
            "matiere" to "jean",
            "motif" to 1,
            "sorti" to 1,
            "style" to "BCBG",
            "temperature" to "modérée",
            "temps" to "ensoleille",
            "type" to "Chemisier court",
            "vetement" to "hauts"
        )
        cities.document("02").set(data2)

        val data3 = hashMapOf(
            "couleur" to "jaune",
            "matiere" to "dentelle",
            "motif" to 1,
            "sorti" to 1,
            "style" to "casual",
            "temperature" to "modéré",
            "temps" to "ensoleille",
            "type" to "Robe courte",
            "vetement" to "ensembles"
        )
        cities.document("03").set(data3)

        val data4 = hashMapOf(
            "couleur" to "colorée",
            "matiere" to "velours",
            "motif" to 1,
            "sorti" to 0,
            "style" to "casual",
            "temperature" to "froid",
            "temps" to "ensoleille",
            "type" to "Veste de velours",
            "vetement" to "vestes"
        )
        cities.document("04").set(data4)

        val data5 = hashMapOf(
            "couleur" to "noir",
            "matiere" to "jean",
            "motif" to 1,
            "sorti" to 1,
            "style" to "sexy",
            "temperature" to "chaud",
            "temps" to "ensoleille",
            "type" to "Sandales",
            "vetement" to "chaussures"
        )
        cities.document("05").set(data5)

        val data6 = hashMapOf(
            "couleur" to "rose",
            "matiere" to "jean",
            "motif" to 1,
            "sorti" to 0,
            "style" to "mignon",
            "temperature" to "chaud",
            "temps" to "ensoleille",
            "type" to "Maillot de Bain Brassière",
            "vetement" to "dessous"
        )
        cities.document("06").set(data6)

        val data7 = hashMapOf(
            "couleur" to "vert",
            "matiere" to "jean",
            "motif" to 1,
            "sorti" to 1,
            "style" to "mignon",
            "temperature" to "chaud",
            "temps" to "ensoleille",
            "type" to "T-Shirt",
            "vetement" to "hauts"
        )
        cities.document("07").set(data7)

    }

    private fun onClickedClothes(clothe: Clothes) {

    }

    //private fun SortClothes

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
