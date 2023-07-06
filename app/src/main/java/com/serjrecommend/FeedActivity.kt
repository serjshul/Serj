package com.serjrecommend

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.serjrecommend.music.MusicAdapter

/**
 *  FEED ACTIVITY
 *
 *  Activity contains a GridView containing clickable cards with Serj's music recommendations.
 */
class FeedActivity : AppCompatActivity() {

    // GridView that contains clickable cards
    private lateinit var gridViewMusic: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        // Initialize the GridView
        gridViewMusic = findViewById(R.id.gridViewMusic)
        // Set the custom adapter for the music
        gridViewMusic.adapter = MusicAdapter(this)
        // Set an opening the Full Music Activity on ItemClick
        gridViewMusic.setOnItemClickListener { adapterView, view, position, id ->
            // Sending image id to FullScreenActivity
            val intent = Intent(this, FullMusicActivity::class.java)
            intent.putExtra("id", position)
            startActivity(intent)
        }
    }
}