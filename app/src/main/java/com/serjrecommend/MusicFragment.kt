package com.serjrecommend

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.data.music.MusicData
import com.serjrecommend.data.music.MusicAdapter
import com.serjrecommend.data.music.MusicModel

private const val ARG_OBJECT = "Music"


/**
 * MUSIC FRAGMENT
 *
 * The fragment contains a header, a line with types and tags, and a RecyclerView with cards that
 * contain media content.
 *
 * The cards contain:
 * - title
 * - musician's name
 * - cover
 *
 * All cards in the RecyclerView are clickable. When the user click on a card, there are transition
 * to `FullMusicActivity`.
 */
class MusicFragment : Fragment() {

    // Music data
    private lateinit var data: ArrayList<MusicModel>

    // A RecyclerView that contains clickable cards
    private lateinit var cards : RecyclerView

    // Some cards views
    private lateinit var types: ArrayList<TextView>
    private lateinit var tags: ArrayList<TextView>

    // Layout where we add tags
    private lateinit var tagsLayout: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.music_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            // Initializing of some things
            types = arrayListOf()
            tags = arrayListOf()
            tagsLayout = view.findViewById(R.id.tags_layout)

            // Setting the RecyclerView
            cards = view.findViewById(R.id.cards)
            // Create a vertical Linear Layout Manager
            cards.layoutManager = GridLayoutManager(view.context, 2)

            // Setting the media data
            data = MusicData.getMusicData()
            val adapter = MusicAdapter(data)
            cards.adapter = adapter

            // Set setOnClickListener on cards view
            adapter.setOnClickListener(object : MusicAdapter.OnClickListener {
                override fun onClick(position: Int, model: MusicModel) {
                    val intent = Intent(view.context, FullMusicActivity::class.java)
                    // Passing the data to the EmployeeDetails Activity
                    intent.putExtra("data", model)
                    startActivity(intent)
                }
            })

            // Adding types and tags
            storeTags(types, true)
            storeTags(tags, false)
        }
    }

    // Creates new tagView with a text and defined left margin
    private fun setNewTag(tagType: Int, leftMargin: Int, text: String): TextView {
        // Creating new view of the tag
        val tagView: View = LayoutInflater.from(requireView().context).inflate(tagType, null)

        // Defining tag's TextView and setting the text
        val tag = tagView.findViewById<TextView>(R.id.tag)
        tag.text = text
        // Setting margins to the tag by MarginLayoutParams
        val params = tag.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(leftMargin, 0, 0, 0)
        tag.layoutParams = params

        // Setting tag to tags_layout
        tagsLayout.addView(tagView)

        return tag
    }

    // Getting all tags and types from data and setting it to tags_layout
    private fun storeTags(views: ArrayList<TextView>, isType: Boolean) {
        // Array of unique tag's names
        val tags = arrayListOf<String>()

        // Store names from the data
        for (item in data) {
            if (isType) {
                if (!tags.contains(item.type)) {
                    tags.add(item.type)
                }
            } else {
                for (tag in item.tags) {
                    if (!tags.contains(tag)) {
                        tags.add(tag)
                    }
                }
            }
        }

        // Sorting names
        tags.sort()

        // Setting tags to tags_layout
        for (tag in tags) {
            if (isType) {
                views.add(setNewTag(R.layout.tag_white, 30, tag))
            } else {
                views.add(setNewTag(R.layout.tag_transparent, 30, tag))
            }
        }
    }
}