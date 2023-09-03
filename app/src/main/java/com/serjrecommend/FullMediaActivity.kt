package com.serjrecommend

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.serjrecommend.data.media.MediaModel
import com.serjrecommend.data.media.MediaParagraphModel
import java.io.Serializable


/**
 * FULL MEDIA ACTIVITY
 *
 * This activity is a page that contains information about one of the media recommendations:
 * - title
 * - type and tags
 * - cover
 * - short description
 * - paragraphs containing: title, cover and text
 *
 * The transition to the activity is carried out by clicking on one of the RecyclerView cards
 * in the MediaFragment. At the same time, data about the selected recommendation is transmitted
 * in the current activity (via Intent).
 */
class FullMediaActivity : AppCompatActivity() {

    // Some views that represent an media recommendation
    private lateinit var title: TextView
    private lateinit var rating: TextView
    private lateinit var production: TextView
    private lateinit var cover: ImageView
    private lateinit var description: TextView
    private lateinit var type: TextView
    private lateinit var tags: ArrayList<TextView>

    // Button that finish this activity
    private lateinit var backButton: ImageButton

    // Layout where we add paragraphs
    private lateinit var mainLayout: LinearLayout
    // Layout where we add tags
    private lateinit var tagLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_media)

        // Getting the current Intent
        val intent = intent
        val data: MediaModel? = intent.serializable("data")

        // Setting the backButton action (finish activity)
        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        // Define activity's views
        mainLayout = findViewById(R.id.main_layout)
        tagLayout = findViewById(R.id.tags_layout)
        title = findViewById(R.id.title)
        rating = findViewById(R.id.rating)
        production = findViewById(R.id.production)
        cover = findViewById(R.id.cover)
        description = findViewById(R.id.description)
        tags = arrayListOf()

        // Filling views by data
        if (data != null) {
            // Setting the major data
            title.text = data.title
            production.text = data.production
            cover.setImageResource(data.coverId)
            description.text = data.description

            // Setting rating of the media and the color of the text
            rating.text = data.rating.toString()
            when {
                data.rating >= 8.0 -> rating.setTextColor(Color.YELLOW)
                data.rating >= 6.0 -> rating.setTextColor(Color.GREEN)
                else -> rating.setTextColor(Color.RED)
            }

            // Adding type of the recommendation to a tags_layout
            type = setNewTag(R.layout.tag_white, 35, data.type)
            // Adding tags of the recommendation to a tags_layout
            for (tag in data.tags) {
                tags.add(setNewTag(R.layout.tag_transparent, 30, tag))
            }
            // Adding paragraphs of the recommendation to a main_layout
            for (paragraph in data.paragraphs) {
                setNewParagraph(paragraph)
            }
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
        tagLayout.addView(tagView)

        return tag
    }

    // Creates new paragraph and adds to the main_layout
    private fun setNewParagraph(paragraph: MediaParagraphModel) {
        // Creating new view of the paragraph
        val paragraphView: View = LayoutInflater.from(this).inflate(R.layout.paragraph_with_image, null)

        // Defining paragraph's TextView and setting the text
        val paragraphTitle = paragraphView.findViewById<TextView>(R.id.title)
        paragraphTitle.text = paragraph.title
        val paragraphCover = paragraphView.findViewById<ImageView>(R.id.cover)
        paragraphCover.setImageResource(paragraph.coverId)
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