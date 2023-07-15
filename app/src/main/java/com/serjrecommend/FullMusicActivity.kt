package com.serjrecommend

import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.serjrecommend.music.MusicAdapter


/**
 *  FULL MUSIC ACTIVITY
 *
 *  An activity that opens when a user click on an Item in the GridView.
 *  Shows an information about one of the Serj's recommendations.
 */
class FullMusicActivity : AppCompatActivity() {

    // The VideoView shows to a user music video with the chosen song
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_music)

        // Getting the current Intent and the position of chosen song
        val intent = intent
        val position = intent.extras!!.getInt("id")

        // Set an MusicAdapter
        val musicAdapter = MusicAdapter(this)
        // The Uri object that contains link to the music video
        val uri = Uri.parse("android.resource://${packageName}/${musicAdapter.thumbIds[position].getVideoId()}")

        // Initialize the videoView, set link to the music video and start it
        videoView = findViewById(R.id.videoView)
        videoView.setVideoURI(uri)
        videoView.start()
    }
}