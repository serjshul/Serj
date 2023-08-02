package com.serjrecommend.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.R


/**
 * PLACES CAROUSEL ADAPTER
 *
 * The adapter provides a binding from an places recommendation photos set to view (`CardView`) that
 * is displayed within a `RecyclerView`.
 */
class PlacesCarouselAdapter(private val data: List<Int>) : RecyclerView.Adapter<PlacesCarouselAdapter.ViewHolder>() {

    // The view describes an item view and metadata about its place within the RecyclerView

    // The view describes an item view and metadata about its place within the RecyclerView
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    // Creates a new ViewHolder and initializes some private fields to be used by RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.places_gallery_item, parent, false)

        return ViewHolder(viewHolder)
    }

    // Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Setting the data
        holder.image.setImageResource(data[position])
    }

    // Returns the total number of items in the data set held by the adapter
    override fun getItemCount(): Int {
        return data.size
    }
}