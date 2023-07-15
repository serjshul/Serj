package com.serjrecommend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 *  FEED ACTIVITY
 *
 *  Activity contains a GridView containing clickable cards with Serj's music recommendations.
 */
class FeedActivity : AppCompatActivity() {

    // adapter is a custom FragmentPagerAdapter
    private lateinit var adapter: TabAdapter
    // viewPager is a layout manager that allows the user to flip left and right through pages of data
    private lateinit var viewPager: ViewPager2
    // tabLayout allows to move to the pages
    private lateinit var tabLayout: TabLayout
    // Names of tabs that displayed on the top panel
    private val tabNames: Array<String> = arrayOf(
        "Music",
        "Media",
        "Places"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        // Initialization of viewPager and setting the TabAdapter
        viewPager = findViewById(R.id.pager)
        adapter = TabAdapter(this)
        viewPager.adapter = adapter

        // Initialization of tabLayout and setting the names
        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}