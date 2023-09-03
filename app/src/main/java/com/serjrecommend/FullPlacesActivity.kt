package com.serjrecommend

import android.content.Intent
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.serjrecommend.adapters.CarouselAdapter
import com.serjrecommend.data.places.PlacesModel
import com.serjrecommend.data.places.PlacesParagraphModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import java.io.Serializable


/**
 * FULL PLACES ACTIVITY
 *
 * This activity is a page that contains information about one of the places recommendations:
 * - title
 * - type and tags
 * - gallery
 * - short description
 * - address
 * - metro station
 * - social media
 * - paragraphs containing: title and text
 *
 * The transition to the activity is carried out by clicking on one of the RecyclerView cards
 * in the PlacesFragment. At the same time, data about the selected recommendation is transmitted
 * in the current activity (via Intent).
 */
class FullPlacesActivity : AppCompatActivity() {

    // Some views that represent an places recommendation
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var location: TextView
    private lateinit var carousel: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var address: TextView
    private lateinit var metro: TextView
    private lateinit var metroPicture: ImageView
    private lateinit var socialMedia: TextView
    private lateinit var tags: ArrayList<TextView>

    // Button that finish this activity
    private lateinit var backButton: ImageButton

    // Layout where we add paragraphs
    private lateinit var mainLayout: LinearLayout
    // Layout where we add tags
    private lateinit var tagLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_places)

        // Getting the current Intent
        val intent = intent
        val data: PlacesModel? = intent.serializable("data")

        // Setting the backButton action (finish activity)
        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        // Define activity's views
        mainLayout = findViewById(R.id.main_layout)
        tagLayout = findViewById(R.id.tags_layout)
        title = findViewById(R.id.title)
        description = findViewById(R.id.description)
        carousel = findViewById(R.id.carousel)
        address = findViewById(R.id.address)
        metro = findViewById(R.id.metro)
        metroPicture = findViewById(R.id.metro_picture)
        socialMedia = findViewById(R.id.social_media)
        tags = arrayListOf()

        // Filling views by data
        if (data != null) {
            // Setting the major data
            title.text = data.title
            description.text = data.description
            address.text = data.address
            metro.text = data.metro
            socialMedia.text = data.socialMedia
            // Setting data to carousel via PlacesCarouselAdapter
            carousel.adapter = CarouselAdapter(data.gallery)
            // Setting metroPicture depending on location
            when (data.location) {
                "Санкт-Петербург" -> metroPicture.setImageResource(R.drawable.places_logo_metro_spb)
                "Saint-Petersburg" -> metroPicture.setImageResource(R.drawable.places_logo_metro_spb)
                "Москва" ->  metroPicture.setImageResource(R.drawable.places_logo_metro_moscow)
                "Moscow" -> metroPicture.setImageResource(R.drawable.places_logo_metro_moscow)
                else -> metroPicture.setImageResource(R.drawable.places_logo_metro_moscow)
            }

            // Adding type of the recommendation to a tags_layout
            location = setNewTag(R.layout.tag_white, 35, data.location)
            // Adding tags of the recommendation to a tags_layout
            for (type in data.types) {
                tags.add(setNewTag(R.layout.tag_transparent, 30, type))
            }
            // Adding paragraphs of the recommendation to a main_layout
            for (paragraph in data.paragraphs) {
                setNewParagraph(paragraph)
            }
        }

        // Setting the dotsIndicator
        dotsIndicator = findViewById(R.id.banner_dots)
        dotsIndicator.attachTo(carousel)

        /*
        // Setting Page Transformer to the carousel
        val compositePageTransformer = CompositePageTransformer()
        // Distance between items
        compositePageTransformer.addTransformer(MarginPageTransformer((10 * Resources.getSystem().displayMetrics.density).toInt()))
        // Zoom-in effect
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        // Setting the carousel parameters
        carousel.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            setPadding(50, 0, 50, 0) // Padding settings
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
            setPageTransformer(compositePageTransformer) // Setting Page Transformer to the carousel
        }

         */
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
        tagLayout.addView(tagView)

        return tag
    }

    // Creates new paragraph and adds to the main_layout
    private fun setNewParagraph(paragraph: PlacesParagraphModel) {
        // Creating new view of the paragraph
        val paragraphView: View = LayoutInflater.from(this).inflate(R.layout.paragraph_without_image, null)

        // Defining paragraph's TextView and setting the text
        val paragraphTitle = paragraphView.findViewById<TextView>(R.id.title)
        paragraphTitle.text = paragraph.title
        val paragraphText = paragraphView.findViewById<TextView>(R.id.text)
        paragraphText.text = HtmlCompat.fromHtml(paragraph.text, HtmlCompat.FROM_HTML_MODE_LEGACY)

        // Setting paragraph to main_layout
        mainLayout.addView(paragraphView)
    }

    // Makes getSerializableExtras() not deprecated
    private inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }
}