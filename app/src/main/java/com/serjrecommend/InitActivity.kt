package com.serjrecommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


/**
 *  INITIAL ACTIVITY
 *
 *  Activities opens when a user opens the applications. Contains the welcome TextView, Button
 *  that opens the Feed Activity and background ImageView.
 */
class InitActivity : AppCompatActivity() {
    private lateinit var buttonEnter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        // Button that opens the Feed Activity
        buttonEnter = findViewById(R.id.buttonEnter)
        buttonEnter.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}