package com.serjrecommend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MusicAdapter(context: Context, musicModelArrayList: ArrayList<MusicModel>) :
	ArrayAdapter<MusicModel?>(context, 0, musicModelArrayList as List<MusicModel?>) {

	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		var listItemView = convertView

		if (listItemView == null) {
			listItemView = LayoutInflater.from(context).inflate(
				R.layout.music_item,
				parent,
				false
			)
		}

		val musicModel: MusicModel? = getItem(position)
		val textViewMusic = listItemView!!.findViewById<TextView>(R.id.textViewMusic)
		val imageViewMusic = listItemView.findViewById<ImageView>(R.id.imageViewMusic)

		if (musicModel != null) {
			textViewMusic.text = musicModel.getMusicName()
		}
		if (musicModel != null) {
			imageViewMusic.setImageResource(musicModel.getMusicImageId())
		}
		return listItemView
	}
}