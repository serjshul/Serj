package com.serjrecommend

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.serjrecommend.music.MusicAdapter

private const val ARG_OBJECT = "Music"

/**
 * MUSIC FRAGMENT
 *
 * It contains a GridView with clickable cards (Serj's music recommendations).
 *
 * The fragment uses the fragment_music.xml layout. When moving to the next activity
 * (FullMusicActivity) sends an ID by putExtra() in Intent.
 */
class MusicFragment : Fragment() {

    // GridView that contains clickable cards
    private lateinit var gridViewMusic: GridView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            // Initialize the GridView
            gridViewMusic = view.findViewById(R.id.gridViewMusic)
            // Set the custom adapter for the music
            gridViewMusic.adapter = MusicAdapter(view.context)
            // Set an opening the Full Music Activity on ItemClick
            gridViewMusic.setOnItemClickListener { adapterView, view, position, id ->
                // Sending image id to FullScreenActivity
                val intent = Intent(view.context, FullMusicActivity::class.java)
                intent.putExtra("id", position)
                startActivity(intent)
            }
        }
    }
}