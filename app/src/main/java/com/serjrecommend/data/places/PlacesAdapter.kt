package com.serjrecommend.data.places

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.R


/**
 * PLACES ADAPTER
 *
 * The adapter provides a binding from an places recommendation photos set to view (`CardView`) that
 * is displayed within a `RecyclerView` on to `PlacesFragment`.
 */
class PlacesAdapter(private val data: ArrayList<PlacesModel>) : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    // The onClickListener that defines onClick method
    private var onClickListener: OnClickListener? = null

    // The view describes an item view and metadata about its media within the RecyclerView
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val location: TextView = itemView.findViewById(R.id.location)
        val cover: ImageView = itemView.findViewById(R.id.cover)
        val type: TextView = itemView.findViewById(R.id.type)
    }

    // OnClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: PlacesModel)
    }

    // Creates a new ViewHolder and initializes some private fields to be used by RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_places, parent, false)

        return ViewHolder(view)
    }

    // Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting data depending on position
        val itemData = data[position]

        // Setting the data
        holder.title.text = itemData.title
        holder.location.text = itemData.types[0]
        holder.cover.setImageResource(itemData.coverId)
        holder.type.text = itemData.types.joinToString(separator = ", ")

        // Setting the onClickListener
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, itemData)
            }
        }
    }

    // Returns the total number of items in the data set held by the adapter
    override fun getItemCount(): Int {
        return data.size
    }

    // Bind the onclickListener
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
}