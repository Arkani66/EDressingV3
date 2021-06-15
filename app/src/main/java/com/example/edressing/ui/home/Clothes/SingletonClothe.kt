package com.example.edressing.ui.home.Clothes

import com.example.edressing.Edressing
import com.example.edressing.ui.models.Vetements
import com.example.meteoguesser.MeteoAPI.MeteoAPI
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 *Created by $(USER) on $(DATE).
 */
class SingletonClothe {
    companion object{
        private val temperature: String
        private val temps: String
        private val COLLECTION_NAME = "vetements"
        private val db = Firebase.firestore
        //var mlistClothe: ArrayList<Clothes> = recupListClothes(temperature,temps)

        private fun recupListClothes(mcaract: Caracters): ArrayList<Clothes> {
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
    }


}