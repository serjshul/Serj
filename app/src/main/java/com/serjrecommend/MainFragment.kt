package com.serjrecommend

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.serjrecommend.data.fragment_main.banners.BannersAdapter
import com.serjrecommend.data.fragment_main.banners.BannersData
import com.serjrecommend.data.fragment_main.banners.BannersModel
import com.serjrecommend.data.fragment_main.main_categories.*
import com.serjrecommend.data.music.MusicDataModel
import com.serjrecommend.data.fragment_main.stories.StoriesData
import com.serjrecommend.data.fragment_main.stories.StoriesModel
import com.serjrecommend.data.media.MediaModel
import com.serjrecommend.data.music.MusicData
import com.serjrecommend.data.places.PlacesModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Some things for banners
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var bannersAdapter: BannersAdapter

    // Data arrays
    private lateinit var bannersData: ArrayList<BannersModel>
    private lateinit var storiesData: ArrayList<StoriesModel>
    private lateinit var categoriesData: List<MainCategoriesModel>

    // Views arrays
    private lateinit var stories: ArrayList<View>
    private lateinit var categories: ArrayList<View>

    // Layout where tags are added
    private lateinit var storiesLayout: LinearLayout
    private lateinit var categoriesLayout: LinearLayout
    private lateinit var itemsLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize current view
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Initialize viewPager where banners are located
        viewPager = view.findViewById(R.id.banners)
        // Setting the banners adapter
        bannersData = BannersData.getBannersData()
        bannersAdapter = BannersAdapter(bannersData)
        viewPager.adapter = bannersAdapter
        // Setting the dotsIndicator
        dotsIndicator = view.findViewById(R.id.banner_dots)
        dotsIndicator.attachTo(viewPager)

        // Adding stories to the fragment
        stories = arrayListOf()
        storiesLayout = view.findViewById(R.id.stories_layout)
        storiesData = StoriesData.getStoriesData()
        // Store stories data and add it to the storiesLayout
        for (i in storiesData.indices) {
            val currentStory = if (i == storiesData.size - 1) {
                setNewStory(inflater, i, 30)
            } else {
                setNewStory(inflater, i, 0)
            }
            stories.add(currentStory)
        }

        val musicData = MusicData()
        Handler(Looper.getMainLooper()).postDelayed({
            categoriesData = MainCategoriesData.getMainMusicCategoriesData(musicData)
            dataUpdate(inflater)
            println("all downloaded")
            println("all downloaded")
            println("all downloaded")
            println("all downloaded")
        }, 5000)

        return view
    }

    private fun dataUpdate(inflater: LayoutInflater) {
        // Adding categories to the fragment
        categories = arrayListOf()
        categoriesLayout = requireView().findViewById(R.id.feed_layout)
        // Store categories data and add it to the categoriesLayout
        for (i in categoriesData.indices) {
            categories.add(setCategory(inflater, i))
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // Creates new story with a header and cover
    private fun setNewStory(
        inflater: LayoutInflater, position: Int, rightPadding: Int
    ): View {
        // Creating new view of the story
        val storyView: View = inflater.inflate(R.layout.item_story, null)

        // Defining story's header and setting the text
        val header = storyView.findViewById<TextView>(R.id.title)
        header.text = storiesData[position].title
        // Defining story's cover and setting the image
        val cover = storyView.findViewById<ImageView>(R.id.cover)
        cover.setImageResource(storiesData[position].coverId)

        // Setting paddings at the story
        storyView.setPadding(30, 30, rightPadding, 0)

        // Setting the setOnClickListener
        storyView.setOnClickListener { viewClick ->
            // Jump to the StoryActivity with extras
            val intent = Intent(viewClick.context, StoryActivity::class.java)
            intent.putExtra("data", storiesData[position])
            startActivity(intent)
            // Remove the gradient of the story
            val gradient = storyView.findViewById<ImageView>(R.id.gradient_cover)
            gradient.setImageResource(R.color.transparent)
        }

        // Setting tag to storiesLayout
        storiesLayout.addView(storyView)

        return storyView
    }

    // Creates new category
    private fun setCategory(inflater: LayoutInflater, position: Int): View {
        // Getting current data
        val currentCategoryData = categoriesData[position]
        println(currentCategoryData)

        // Creating new view of the category (casual or crossing)
        val categoryView: View = when (currentCategoryData.typeOfLayout) {
            "Crossing" -> {
                when (currentCategoryData.typeOfItem) {
                    "Music" -> inflater.inflate(R.layout.item_main_category_crossing_music, null)
                    "Media" -> inflater.inflate(R.layout.item_main_category_crossing_media, null)
                    else -> inflater.inflate(R.layout.item_main_category_crossing_music, null)
                }
            }
            else -> {
                when (currentCategoryData.typeOfItem) {
                    "Music" -> inflater.inflate(R.layout.item_main_category_casual_places, null)
                    "Media" -> inflater.inflate(R.layout.item_main_category_casual_places, null)
                    else -> inflater.inflate(R.layout.item_main_category_casual_places, null)
                }
            }
        }

        // Defining category's header and setting the text
        val header = categoryView.findViewById<TextView>(R.id.header)
        header.text = currentCategoryData.title
        header.setTextColor(resources.getColor(currentCategoryData.titleColor))
        // Defining category's cover and setting the image if it's crossing category
        if (currentCategoryData.typeOfLayout == "Crossing") {
            val cover = categoryView.findViewById<ImageView>(R.id.cover)
            cover.setImageResource(currentCategoryData.coverId)
        }

        // Setting current itemsLayout
        itemsLayout = categoryView.findViewById(R.id.items_layout)
        // Setting current items to the category
        for (i in currentCategoryData.items.indices) {
            val type = currentCategoryData.typeOfItem
            val item = currentCategoryData.items[i]
            when (i) {
                0 -> setItem(inflater, type, item, 30, 15)
                currentCategoryData.items.size - 1 -> setItem(inflater, type, item, 0, 60)
                else -> setItem(inflater, type, item, 0, 15)
            }
        }

        // Setting category to categoriesLayout
        categoriesLayout.addView(categoryView)

        return categoryView
    }

    // Creates new item to current itemsLayout
    private fun setItem(
        inflater: LayoutInflater, typeOfItem: String, data: Any, leftPadding: Int, rightPadding: Int
    ): View {
        // Creating new view of the music item
        val itemView: View = when (typeOfItem) {
            "Music" -> inflater.inflate(R.layout.item_main_category_music, null)
            "Media" -> inflater.inflate(R.layout.item_main_category_media, null)
            else -> inflater.inflate(R.layout.item_main_category_places, null)
        }

        // Specialize current item depending on typeOfItem
        when (typeOfItem) {
            "Music" -> {
                // Cast current itemData
                val itemData = data as MusicDataModel

                // Defining item's title and setting the text
                val title = itemView.findViewById<TextView>(R.id.title)
                title.text = itemData.title
                // Defining item's musician and setting the text
                val musician = itemView.findViewById<TextView>(R.id.musician)
                musician.text = itemData.musician
                // Defining item's cover and setting the image
                val cover = itemView.findViewById<ImageView>(R.id.cover)
                val coverBitmap = BitmapFactory.decodeFile(itemData.coverId!!.absolutePath)
                cover.setImageBitmap(coverBitmap)

                // Setting the setOnClickListener
                itemView.setOnClickListener { viewClick ->
                    // Jump to the FullMusicActivity with extras
                    val intent = Intent(viewClick.context, FullMusicActivity::class.java)
                    intent.putExtra("data", itemData)
                    startActivity(intent)
                }
            }
            "Media" -> {
                // Cast current itemData
                val itemData = data as MediaModel

                // Defining item's title and setting the text
                val title = itemView.findViewById<TextView>(R.id.title)
                title.text = itemData.title
                // Defining item's description and setting the text
                val description = itemView.findViewById<TextView>(R.id.description)
                description.text = itemData.description
                // Defining item's cover and setting the image
                val cover = itemView.findViewById<ImageView>(R.id.cover)
                cover.setImageResource(itemData.coverId)

                // Setting the setOnClickListener
                itemView.setOnClickListener { viewClick ->
                    // Jump to the FullMusicActivity with extras
                    val intent = Intent(viewClick.context, FullMediaActivity::class.java)
                    intent.putExtra("data", itemData)
                    startActivity(intent)
                }
            }
            else -> {
                // Cast current itemData
                val itemData = data as PlacesModel

                // Defining item's title and setting the text
                val title = itemView.findViewById<TextView>(R.id.title)
                title.text = itemData.title
                // Defining item's description and setting the text
                val description = itemView.findViewById<TextView>(R.id.description)
                description.text = itemData.description
                // Defining item's cover and setting the image
                val cover = itemView.findViewById<ImageView>(R.id.cover)
                cover.setImageResource(itemData.coverId)

                // Setting the setOnClickListener
                itemView.setOnClickListener { viewClick ->
                    // Jump to the FullPlacesActivity with extras
                    val intent = Intent(viewClick.context, FullPlacesActivity::class.java)
                    intent.putExtra("data", itemData)
                    startActivity(intent)
                }
            }
        }

        // Setting the paddings
        itemView.setPadding(leftPadding, 0, rightPadding, 0)

        // Setting tag to itemsLayout
        itemsLayout.addView(itemView)

        return itemView
    }
}