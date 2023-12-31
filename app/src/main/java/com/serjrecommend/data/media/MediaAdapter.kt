package com.serjrecommend.data.media

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.R


/**
 * MEDIA ADAPTER
 *
 * The adapter provides a binding from an media recommendation photos set to view (`CardView`) that
 * is displayed within a `RecyclerView` on to `MediaFragment`.
 */
class MediaAdapter(private val data: ArrayList<MediaModel>): RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    // The onClickListener that defines onClick method
    private var onClickListener: OnClickListener? = null

    // The view describes an item view and metadata about its media within the RecyclerView
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cover: ImageView = itemView.findViewById(R.id.cover)
        val title: TextView = itemView.findViewById(R.id.title)
        val production: TextView = itemView.findViewById(R.id.production)
        val rating: TextView = itemView.findViewById(R.id.rating)
    }

    // OnClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, model: MediaModel)
    }

    // Creates a new ViewHolder and initializes some private fields to be used by RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false)

        return ViewHolder(view)
    }

    // Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Getting data depending on position
        val itemData = data[position]

        // Setting the data
        holder.cover.setImageResource(itemData.coverId)
        holder.title.text = itemData.title
        holder.production.text = itemData.production
        holder.rating.text = itemData.rating.toString()
        when {
            itemData.rating >= 8.0 -> holder.rating.setTextColor(holder.itemView.resources.getColor(R.color.rating_yellow))
            itemData.rating >= 6.0 -> holder.rating.setTextColor(holder.itemView.resources.getColor(R.color.rating_green))
            else -> holder.rating.setTextColor(holder.itemView.resources.getColor(R.color.rating_red))
        }

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