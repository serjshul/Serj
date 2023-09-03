package com.serjrecommend.data.fragment_search.tags

import com.serjrecommend.R


data class TagModel(var title: String, var coverId: Int): java.io.Serializable


object TagData {

    // Creates an Arraylist of media data and returns it
    fun getTagData(): ArrayList<TagModel> {
        // ArrayList of media recommendations
        val data = ArrayList<TagModel>()

        // Data collection
        data.add(TagModel("House", R.drawable.tag_music_house))
        data.add(TagModel("R&B", R.drawable.tag_music_r_and_b))

        return data
    }
}