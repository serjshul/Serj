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
import com.serjrecommend.data.places.PlacesData
import com.serjrecommend.data.places.PlacesModel


/**
 * PLACES FRAGMENT
 *
 * The fragment contains a header, a line with types and tags, and a RecyclerView with cards that
 * contain places content.
 *
 * The cards contain:
 * - title
 * - type
 * - cover
 * - short description
 *
 * All cards in the RecyclerView are clickable. When the user click on a card, there are transition
 * to `FullPlacesActivity`.
 */
class FeedPlacesActivity : AppCompatActivity() {

    // Places data
    private lateinit var data: ArrayList<PlacesModel>

    // A RecyclerView that contains clickable cards
    private lateinit var cards : RecyclerView

    // Some cards views
    private lateinit var types: ArrayList<TextView>
    private lateinit var tags: ArrayList<TextView>
    private lateinit var categories: MutableMap<String, ArrayList<PlacesModel>>

    // Layout where we add tags
    private lateinit var tagsLayout: LinearLayout
    private lateinit var categoriesLayout: LinearLayout
    private lateinit var itemsLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_places)

        // Initializing of some things
        types = arrayListOf()
        tags = arrayListOf()

        categoriesLayout = findViewById(R.id.categories_layout)

        // Setting the music data
        data = PlacesData.getPlacesData() as ArrayList<PlacesModel>
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
    private fun storeCategories(data: ArrayList<PlacesModel>): MutableMap<String, ArrayList<PlacesModel>> {
        val categories = mutableMapOf<String, ArrayList<PlacesModel>>()

        for (item in data) {
            for (type in item.types) {
                if (categories.containsKey(type)) {
                    categories[type]!!.add(item)
                } else {
                    categories[type] = arrayListOf(item)
                }
            }
        }

        return categories
    }

    // Creates new category
    private fun setCategory(name: String): View {
        // Creating new view of the category (casual or crossing)
        val categoryView: View = LayoutInflater.from(this).inflate(R.layout.item_main_category_casual_places, null)

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
        itemData: PlacesModel, leftPadding: Int, rightPadding: Int
    ): View {
        // Creating new view of the music item
        val itemView: View = LayoutInflater.from(this).inflate(R.layout.item_places, null)

        // Defining item's title and setting the text
        val title = itemView.findViewById<TextView>(R.id.title)
        title.text = itemData.title
        //
        val type = itemView.findViewById<TextView>(R.id.type)
        type.text = itemData.types.joinToString(separator = ", ")
        //
        val location = itemView.findViewById<TextView>(R.id.location)
        location.text = itemData.location
        // Defining item's cover and setting the image
        val cover = itemView.findViewById<ImageView>(R.id.cover)
        cover.setImageResource(itemData.coverId)

        // Setting the setOnClickListener
        itemView.setOnClickListener { viewClick ->
            // Jump to the FullMusicActivity with extras
            val intent = Intent(viewClick.context, FullPlacesActivity::class.java)
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