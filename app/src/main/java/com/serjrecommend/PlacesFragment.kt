package com.serjrecommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.serjrecommend.places.PlacesAdapter

private const val ARG_OBJECT = "Places"

/**
 * PLACES FRAGMENT
 *
 * It contains a GridView with clickable cards (Serj's places recommendations).
 *
 * The fragment uses the fragment_places.xml layout. When moving to the next activity
 * (FullPlacesActivity) sends an ID by putExtra() in Intent.
 */
class PlacesFragment : Fragment() {

    // ListView that contains clickable cards
    lateinit var list : ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            // Initialize the ListView
            list = view.findViewById(R.id.list)
            // Set the custom adapter for the music
            list.adapter = PlacesAdapter(view.context)
        }
    }
}