package com.serjrecommend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.serjrecommend.adapters.TabAdapter


/**
 *  FEED ACTIVITY
 *
 *  This activity contains the presentation of all content in the form of clickable cards, which
 *  provide basic information about some recommendation. Cards are divided into 3 types (such as
 *  all content): music, media and places.
 *
 *  Separation of cards from different categories is implemented by TabLayout and ViewPager2.
 *  The desired fragment (MusicFragment, MediaFragment or PlacesFragment) displays on ViewPager2
 *  by selecting the TabLayout position. All fragments contain all the information about its
 *  content category.
 */
class FeedActivity : AppCompatActivity() {

    // Custom FragmentStateAdapter
    private lateinit var adapter: TabAdapter
    // Layout manager that allows the user to flip left and right through fragments
    private lateinit var viewPager: ViewPager2
    // The layout Provides a horizontal layout to display tabs
    private lateinit var tabLayout: TabLayout

    // Names and icons of tabs that displayed on the panel
    private val tabNames: Array<String> = arrayOf("Music", "Media", "Places")
    private val tabIcons: Array<Int> = arrayOf(
        R.drawable.baseline_library_music_24,
        R.drawable.baseline_laptop_24,
        R.drawable.baseline_map_24
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        // Initialization of viewPager and setting the TabAdapter
        viewPager = findViewById(R.id.view_pager)
        adapter = TabAdapter(this)
        viewPager.adapter = adapter

        // Initialization of tabLayout and setting names and icons by using TabLayoutMediator
        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
            tab.setIcon(tabIcons[position])
        }.attach()
    }
}