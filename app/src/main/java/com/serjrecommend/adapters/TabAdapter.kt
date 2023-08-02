package com.serjrecommend.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.serjrecommend.MediaFragment
import com.serjrecommend.MusicFragment
import com.serjrecommend.PlacesFragment


/**
 * TAB ADAPTER
 *
 * Implementation of `PagerAdapter` that uses a Fragment to manage each page. This class also handles
 * saving and restoring of fragment's state.
 */
class TabAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    // Returns the amount of fragments
    override fun getItemCount(): Int = 3

    // Returns created fragment depending on position
    override fun createFragment(position: Int): Fragment {
        // Initialization of an fragment depending on position
        val fragment = when (position) {
            0 -> MusicFragment()
            1 -> MediaFragment()
            else -> PlacesFragment()
        }

        // Putting the keys in the arguments
        when (position) {
            0 -> fragment.arguments = Bundle().apply { putInt("Music", 1) }
            1 -> fragment.arguments = Bundle().apply { putInt("Media", 2) }
            else -> fragment.arguments = Bundle().apply { putInt("Places", position + 1) }
        }

        return fragment
    }
}