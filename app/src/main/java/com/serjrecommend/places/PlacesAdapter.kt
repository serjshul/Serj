package com.serjrecommend.places

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.serjrecommend.R

/**
 * PLACES ADAPTER
 *
 * PlacesAdapter is used to form places data to Feed Activity.
 */
class PlacesAdapter(private val context: Context) : BaseAdapter() {
    override fun getCount(): Int = thumbIds.size

    override fun getItem(position: Int): Any = thumbIds[position]

    override fun getItemId(position: Int): Long = position.toLong()

    // Update an itemView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get the current itemView
        var itemView = convertView
        if (itemView == null) {
            // LayoutInflater inflates each item to be displayed in ListView
            itemView = LayoutInflater.from(context).inflate(
                R.layout.places_item,
                parent,
                false
            )
        }

        // Form the current itemView
        val placeName = itemView!!.findViewById<TextView>(R.id.name)
        val placeCover = itemView.findViewById<ImageView>(R.id.cover)
        val placeDescription = itemView.findViewById<TextView>(R.id.description)
        // Set params at the current itemView from thumbIds
        placeName!!.text = thumbIds[position].getName()
        placeCover.setImageResource(thumbIds[position].getPhoto())
        placeDescription!!.text = thumbIds[position].getDescription()

        return itemView
    }

    // References to the places data
    var thumbIds = arrayOf(
        PlacesModel(
            "Civil",  "Санкт-Петербург",  R.drawable.civil_photo,  "ул. 8-я Советская 4",
            "Civil — кофейня с завтраками целый день на Площади Восстания."
        ),
        PlacesModel(
            "Civil",  "Санкт-Петербург",  R.drawable.civil_photo,  "ул. 8-я Советская 4",
            "Civil — кофейня с завтраками целый день на Площади Восстания."
        ),
        PlacesModel(
            "Civil",  "Санкт-Петербург",  R.drawable.civil_photo,  "ул. 8-я Советская 4",
            "Civil — кофейня с завтраками целый день на Площади Восстания."
        )
    )
}