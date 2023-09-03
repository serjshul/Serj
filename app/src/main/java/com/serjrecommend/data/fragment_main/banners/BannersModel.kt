package com.serjrecommend.data.fragment_main.banners

import com.serjrecommend.R


data class BannersModel(var title: String, var text: String, var coverId: Int): java.io.Serializable


object BannersData {

    // Creates an Arraylist of media data and returns it
    fun getBannersData(): ArrayList<BannersModel> {
        // ArrayList of media recommendations
        val data = ArrayList<BannersModel>()

        // Data collection
        data.add(
            BannersModel(
                "Вкусный сай-фай",
                "Вкусное сочетание научной фантастики, подростковой драмы и стиля 80-ых США",
                R.drawable.banner_stranger_things_4
            )
        )
        data.add(
            BannersModel(
                "Вкусные места Петербурга",
                "Кофейни, рестораны, бары и тд, в которых супер приятно!",
                R.drawable.places_gallery_civil_3
            )
        )
        data.add(
            BannersModel(
                "Крутые house треки",
                "Пегги Гу и другие!",
                R.drawable.tag_music_house
            )
        )

        return data
    }
}