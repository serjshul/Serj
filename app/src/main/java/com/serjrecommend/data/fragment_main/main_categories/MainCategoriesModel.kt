package com.serjrecommend.data.fragment_main.main_categories

import android.os.Handler
import android.os.Looper
import com.serjrecommend.R
import com.serjrecommend.data.music.MusicData
import com.serjrecommend.data.music.MusicDataModel
import com.serjrecommend.data.places.PlacesData
import kotlin.collections.ArrayList


data class MainCategoriesModel(var typeOfLayout: String, var typeOfItem: String,
                               var title: String, var titleColor: Int, var coverId: Int,
                               var items: List<Any>): java.io.Serializable


object MainCategoriesData {

    // Creates an Arraylist of media data and returns it
    fun getMainMusicCategoriesData(musicData: MusicData): ArrayList<MainCategoriesModel> {
        // ArrayList of media recommendations
        val data = ArrayList<MainCategoriesModel>()

        // Data collection
        //val newMusicFiltered = (MusicData.getMusicData() as ArrayList<MusicModel>).sortBy { it.date } as ArrayList<Any>
        val newMusicFiltered = (musicData.data as ArrayList<MusicDataModel>)
            .sortedByDescending { it.date }

        val newMusic = ArrayList(newMusicFiltered) as ArrayList<Any>
        data.add(
            MainCategoriesModel(
                "Crossing",
                "Music",
                "Новая музыка",
                R.color.palette_brown,
                R.drawable.wide_category_new_music,
                newMusic
            )
        )

        // Data collection
        val spbCoffeePlaces = PlacesData.getPlacesData()
        data.add(
            MainCategoriesModel(
                "Casual",
                "Places",
                "Кофейни Петербурга",
                R.color.palette_brown,
                R.drawable.wide_category_spb_places,
                spbCoffeePlaces
            )
        )

        return data
    }
}