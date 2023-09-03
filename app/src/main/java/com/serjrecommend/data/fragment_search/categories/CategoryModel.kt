package com.serjrecommend.data.fragment_search.categories

import com.serjrecommend.R


data class CategoryModel(var title: String, var coverId: Int): java.io.Serializable


object CategoryData {

    // Creates an Arraylist of media data and returns it
    fun getCategoryData(): ArrayList<CategoryModel> {
        // ArrayList of media recommendations
        val data = ArrayList<CategoryModel>()

        // Data collection
        data.add(CategoryModel("Музыка", R.drawable.category_music_cover))
        data.add(CategoryModel("Медиа", R.drawable.category_media_cover))
        data.add(CategoryModel("Места", R.drawable.category_places_cover))

        return data
    }
}