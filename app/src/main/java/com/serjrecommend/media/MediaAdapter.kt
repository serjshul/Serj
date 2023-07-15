package com.serjrecommend.media

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.serjrecommend.R

/**
 * MEDIA ADAPTER
 *
 * MediaAdapter is used to form media data to Feed Activity.
 */
class MediaAdapter(private val context: Context) : BaseAdapter() {
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
                R.layout.media_item,
                parent,
                false
            )
        }

        // Form the current itemView
        val textView = itemView!!.findViewById<TextView>(R.id.text)
        textView!!.text = thumbIds[position].getDescription()
        val cover = itemView.findViewById<ImageView>(R.id.cover)
        cover.setImageResource(thumbIds[position].getCoverId())

        return itemView
    }

    // References to the media data
    var thumbIds = arrayOf(
        MediaModel(R.drawable.youtube_cover_1, "Шоу для тех, кто травмирован школьными уроками литературы"),
        MediaModel(R.drawable.youtube_cover_2, "Четыре друга откровенно обсуждают волнующие их темы с гостями или без."),
        MediaModel(R.drawable.youtube_cover_1, "Шоу для тех, кто травмирован школьными уроками литературы"),
        MediaModel(R.drawable.youtube_cover_2, "Четыре друга откровенно обсуждают волнующие их темы с гостями или без."),
        MediaModel(R.drawable.youtube_cover_1, "Шоу для тех, кто травмирован школьными уроками литературы"),
        MediaModel(R.drawable.youtube_cover_2, "Четыре друга откровенно обсуждают волнующие их темы с гостями или без.")
    )
}