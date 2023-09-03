package com.serjrecommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.serjrecommend.data.media.MediaData
import com.serjrecommend.data.media.MediaModel


/**
 * MEDIA FRAGMENT
 *
 * The fragment contains a header, a line with types and tags, and a RecyclerView with cards that
 * contain media content.
 *
 * The cards contain:
 * - title
 * - type
 * - cover
 * - short description
 *
 * All cards in the RecyclerView are clickable. When the user click on a card, there are transition
 * to `FullMediaActivity`.
 */
class FeedMediaActivity : AppCompatActivity() {

    // Media data
    private lateinit var data: ArrayList<MediaModel>

    // A RecyclerView that contains clickable cards
    private lateinit var cards : RecyclerView

    // Some cards views
    private lateinit var types: ArrayList<TextView>
    private lateinit var tags: ArrayList<TextView>
    private lateinit var categories: MutableMap<String, ArrayList<MediaModel>>

    // Layout where we add tags
    private lateinit var tagsLayout: LinearLayout
    private lateinit var categoriesLayout: LinearLayout
    private lateinit var itemsLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_media)

        // Initializing of some things
        types = arrayListOf()
        tags = arrayListOf()

        categoriesLayout = findViewById(R.id.categories_layout)

        // Setting the music data
        data = MediaData.getMediaData() as ArrayList<MediaModel>
        categories = storeCategories(data)
        for ((name, items) in categories) {
            setCategory(name)
        }
    }

    // Creates new tagView with a text and defined left margin
    private fun setNewTag(tagType: Int, leftMargin: Int, text: String): TextView {
        // Creating new view of the tag
        val tagView: View = LayoutInflater.from(this).inflate(tagType, null)

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
    private fun storeCategories(data: ArrayList<MediaModel>): MutableMap<String, ArrayList<MediaModel>> {
        val categories = mutableMapOf<String, ArrayList<MediaModel>>()

        for (item in data) {
            if (categories.containsKey(item.category)) {
                categories[item.category]!!.add(item)
            } else {
                categories[item.category] = arrayListOf(item)
            }
        }

        return categories
    }

    // Creates new category
    private fun setCategory(name: String): View {
        // Creating new view of the category (casual or crossing)
        val categoryView: View = LayoutInflater.from(this).inflate(R.layout.item_main_category_casual_media, null)

        // Defining category's header and setting the text
        val header = categoryView.findViewById<TextView>(R.id.header)
        header.text = name

        // Setting current itemsLayout
        itemsLayout = categoryView.findViewById(R.id.items_layout)
        // Setting current items to the category
        for (i in categories[name]!!.indices){
            when (i) {
                0 -> setItem(categories[name]!![i], 30, 15)
                data.size - 1 -> setItem(categories[name]!![i], 0, 60)
                else -> setItem(categories[name]!![i], 0, 15)
            }
        }

        // Setting category to categoriesLayout
        categoriesLayout.addView(categoryView)

        return categoryView
    }

    // Creates new item to current itemsLayout
    private fun setItem(
        itemData: MediaModel, leftPadding: Int, rightPadding: Int
    ): View {
        // Creating new view of the music item
        val itemView: View = LayoutInflater.from(this).inflate(R.layout.item_media, null)

        // Defining item's title and setting the text
        val title = itemView.findViewById<TextView>(R.id.title)
        title.text = itemData.title
        // Defining item's cover and setting the image
        val cover = itemView.findViewById<ImageView>(R.id.cover)
        cover.setImageResource(itemData.coverId)
        //
        val production = itemView.findViewById<TextView>(R.id.production)
        production.text = itemData.production
        //
        val rating = itemView.findViewById<TextView>(R.id.rating)
        rating.text = itemData.rating.toString()
        when {
            itemData.rating >= 8.0 -> rating.setTextColor(itemView.resources.getColor(R.color.rating_yellow))
            itemData.rating >= 6.0 -> rating.setTextColor(itemView.resources.getColor(R.color.rating_green))
            else -> rating.setTextColor(itemView.resources.getColor(R.color.rating_red))
        }

        // Setting the setOnClickListener
        itemView.setOnClickListener { viewClick ->
            // Jump to the FullMusicActivity with extras
            val intent = Intent(viewClick.context, FullMediaActivity::class.java)
            intent.putExtra("data", itemData)
            startActivity(intent)
        }

        // Setting the paddings
        itemView.setPadding(leftPadding, 0, rightPadding, 0)

        // Setting tag to itemsLayout
        itemsLayout.addView(itemView)

        return itemView
    }
}