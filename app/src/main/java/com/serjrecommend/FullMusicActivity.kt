package com.serjrecommend

import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.media.MediaPlayer.OnVideoSizeChangedListener
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.*
import android.view.TextureView.SurfaceTextureListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.serjrecommend.data.music.MusicDataModel
import com.serjrecommend.data.music.MusicParagraphModel
import java.io.File
import java.io.FileDescriptor
import java.io.IOException
import java.io.Serializable


/**
 * FULL MUSIC ACTIVITY
 *
 * This activity is a page that contains information about one of the music recommendations:
 * - title
 * - musician's name
 * - type and tags
 * - cover
 * - text
 *
 * The transition to the activity is carried out by clicking on one of the RecyclerView cards
 * in the MusicFragment. At the same time, data about the selected recommendation is transmitted
 * in the current activity (via Intent).
 */
class FullMusicActivity : AppCompatActivity(), SurfaceTextureListener, OnVideoSizeChangedListener {

    // Some views that represent an music recommendation
    private lateinit var title: TextView
    private lateinit var musician: TextView
    private lateinit var cover: ImageView
    private lateinit var description: TextView
    private lateinit var type: TextView
    private lateinit var tags: ArrayList<TextView>
    private lateinit var quote: TextView

    // Button that finish this activity
    private lateinit var backButton: ImageButton
    // Button that likes the recommendation
    private lateinit var likeButton: ImageButton
    //
    private lateinit var pausePlayButton: ImageButton

    // Things what we need to play the background video
    private lateinit var backgroundVideo: TextureView
    private lateinit var mediaPlayer: MediaPlayer
    //private lateinit var fileDescriptor: FileDescriptor
    private lateinit var videoFile: File

    // Layout where we add paragraphs
    private lateinit var mainLayout: LinearLayout
    // Layout where we add tags
    private lateinit var tagLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_music)

        // Getting the current Intent
        val intent = intent
        val data: MusicDataModel? = intent.serializable("data")

        // Setting the backButton action (finish activity)
        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        pausePlayButton = findViewById(R.id.pause_play_button)
        pausePlayButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                pausePlayButton.setImageResource(R.drawable.baseline_play_arrow_24)
            } else {
                mediaPlayer.start()
                pausePlayButton.setImageResource(R.drawable.baseline_pause_24)
            }
        }

        // Define activity's views
        mainLayout = findViewById(R.id.main_layout)
        tagLayout = findViewById(R.id.tags_layout)
        title = findViewById(R.id.title)
        musician = findViewById(R.id.musician)
        cover = findViewById(R.id.cover)
        likeButton = findViewById(R.id.like_button)
        description = findViewById(R.id.description)
        quote = findViewById(R.id.quote)
        tags = arrayListOf()

        // Define video things
        backgroundVideo = findViewById(R.id.background_video)
        backgroundVideo.surfaceTextureListener = this
        mediaPlayer = MediaPlayer()

        // Filling views by data
        if (data != null) {
            // Setting the major data
            title.text = data.title
            musician.text = data.musician
            description.text = data.description
            description.movementMethod = ScrollingMovementMethod()
            val coverBitmap = BitmapFactory.decodeFile(data.coverId!!.absolutePath)
            cover.setImageBitmap(coverBitmap)
            // fileDescriptor = assets.openFd(data.videoId.toString())
            videoFile = data.videoId!!

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

            quote.text = data.quote.text
            quote.setBackgroundColor(Color.parseColor(data.quote.color))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    // Invoked when a TextureView's SurfaceTexture is ready for use
    override fun onSurfaceTextureAvailable(surfaceTexture: SurfaceTexture, width: Int, height: Int) {
        val surface = Surface(surfaceTexture)
        try {
            mediaPlayer.setSurface(surface)
            mediaPlayer.setDataSource(videoFile.absolutePath)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
            mediaPlayer.isLooping = true
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Invoked when the SurfaceTexture's buffers size changed
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {}

    // Invoked when the specified SurfaceTexture is about to be destroyed
    override fun onSurfaceTextureDestroyed(surfaceTexture: SurfaceTexture): Boolean {
        return false
    }

    // Invoked when the specified SurfaceTexture is updated through SurfaceTexture#updateTexImage()
    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}

    // Called to indicate the video size
    override fun onVideoSizeChanged(mp: MediaPlayer, width: Int, height: Int) {}

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
    private fun setNewParagraph(paragraph: MusicParagraphModel) {
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