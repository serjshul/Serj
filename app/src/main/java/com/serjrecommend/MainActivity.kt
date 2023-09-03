package com.serjrecommend

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


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
class MainActivity : AppCompatActivity() {

    private lateinit var navigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(MainFragment())

        navigation = findViewById(R.id.navigation)
        navigation.selectedItemId = R.id.main
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.main -> {
                    loadFragment(MainFragment())
                    true
                }
                else -> {
                    loadFragment(FavouritesFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}