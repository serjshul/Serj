package com.serjrecommend.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.R
import com.serjrecommend.data.fragment_main.banners.BannersModel


/**
 * PLACES CAROUSEL ADAPTER
 *
 * The adapter provides a binding from an places recommendation photos set to view (`CardView`) that
 * is displayed within a `RecyclerView`.
 */
class CarouselAdapter(private val data: List<Int>): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    // The view describes an item view and metadata about its music within the RecyclerView
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    // Creates a new ViewHolder and initializes some private fields to be used by RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery_places, parent, false)

        return ViewHolder(view)
    }

    // Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting data depending on position
        val itemData = data[position]

        // Setting the data
        holder.image.setImageResource(itemData)
    }

    // Returns the total number of items in the data set held by the adapter
    override fun getItemCount(): Int {
        return data.size
    }
}