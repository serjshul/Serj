package com.serjrecommend

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class FullMusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_music)

        val intent = intent
        val position = intent.extras!!.getInt("id")

        val imageView = findViewById<ImageView>(R.id.full_image_view)
        //imageView.setImageResource(musicAdapter.mThumbIds[position])
    }
}