package com.serjrecommend

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class FeedActivity : AppCompatActivity() {

    private lateinit var gridViewMusic: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        gridViewMusic = findViewById(R.id.gridViewMusic)

        val musicModelArrayLists: ArrayList<MusicModel> = ArrayList()
        musicModelArrayLists.add(MusicModel("Peggy Gou - Nanana", R.drawable.peggy_gou_nanana))
        musicModelArrayLists.add(MusicModel("Beyonce - Heated", R.drawable.beyonce_heated))
        musicModelArrayLists.add(MusicModel("Joji - Glimpse Of Us", R.drawable.joji_glimpse_of_us))

        val adapter = MusicAdapter(this, musicModelArrayLists)
        gridViewMusic.adapter = adapter

        gridViewMusic.setOnItemClickListener { adapterView, view, position, id ->
            // Sending image id to FullScreenActivity
            val intent = Intent(this, FullMusicActivity::class.java)
            intent.putExtra("id", position)
            startActivity(intent)
        }
    }
}