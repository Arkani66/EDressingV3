package com.example.edressing.ui.home.Clothes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edressing.R

/**
 *Created by $(USER) on $(DATE).
 */
class ClothesAdaptater(private var dataSet : List<Clothes>, var listener: ((Clothes) -> Unit)? = null) : RecyclerView.Adapter<ClothesAdaptater.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView : TextView
        val imagecat: ImageView
        init {
            //Define clickListener for the ViewHolder's View
            textView = view.findViewById(R.id.clothe_name)
            imagecat = view.findViewById(R.id.clothe_item_image)

        }
    }

    fun updateList(list: List<Clothes>){
        dataSet = list
        //notifie que la liste a changer et qu'il faut mettre à jour la view
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.clothes_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        //Get element from your dataset at this position and replace the
        //contents of the view widget with that element
        val clothe : Clothes = dataSet[position]
        viewHolder.textView.text = clothe.name
        viewHolder.itemView.setOnClickListener{
            listener?.invoke(clothe)
        }

    }

    override fun getItemCount() = dataSet.size

}
