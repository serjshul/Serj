package com.serjrecommend.music

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.serjrecommend.R

/**
 * MUSIC ADAPTER
 *
 * Music Adapter is used to form music data to Feed Activity.
 */
class MusicAdapter(private val context: Context) : BaseAdapter() {
	override fun getCount(): Int = thumbIds.size

	override fun getItem(position: Int): Any = thumbIds[position]

	override fun getItemId(position: Int): Long = position.toLong()

	// Update an itemView for each item referenced by the Adapter
	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		// Get the current itemView
		var itemView = convertView
		if (itemView == null) {
			// LayoutInflater inflates each item to be displayed in GridView
			itemView = LayoutInflater.from(context).inflate(
				R.layout.music_item,
				parent,
				false
			)
		}

		// Form the current itemView
		val musicName = itemView!!.findViewById<TextView>(R.id.textViewMusic)
		// Set params at the current itemView from thumbIds
		musicName.setTextColor(Color.parseColor(thumbIds[position].getColor()))
		musicName!!.text = thumbIds[position].getName()
		itemView.setBackgroundResource(thumbIds[position].getImageId())

		return itemView
	}

	// References to music data
	var thumbIds = arrayOf(
		MusicModel("Peggy Gou - Nanana",
			R.drawable.cover_peggy_gou_nanana,
			"#000000",
			R.raw.video_peggy_gou_nanana
		),
		MusicModel("Beyonce - Heated",
			R.drawable.cover_beyonce_heated,
			"#FFFFFF",
			R.raw.video_beyonce_heated
		),
		MusicModel("Joji - Glimpse Of Us",
			R.drawable.cover_joji_glimpse_of_us,
			"#FFFFFF",
			R.raw.video_joji_glimpe_of_us
		)
	)
}