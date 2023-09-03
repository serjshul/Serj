package com.serjrecommend.data.fragment_main.stories

import com.serjrecommend.R


data class StoriesModel(var title: String, var coverId: Int,
                        var backgroundId: Int, var text: String): java.io.Serializable


object StoriesData {

    // Creates an Arraylist of media data and returns it
    fun getStoriesData(): ArrayList<StoriesModel> {
        // ArrayList of media recommendations
        val data = ArrayList<StoriesModel>()

        // Data collection
        data.add(
            StoriesModel(
                "История",
                R.drawable.story_gradient_1,
                R.drawable.init_background,
                "Драсте!"
            )
        )
        data.add(
            StoriesModel(
                "История",
                R.drawable.story_gradient_2,
                R.drawable.init_background,
                "Драсте!"
            )
        )
        data.add(
            StoriesModel(
                "История",
                R.drawable.story_gradient_3,
                R.drawable.init_background,
                "Драсте!"
            )
        )
        data.add(
            StoriesModel(
                "История",
                R.drawable.story_gradient_1,
                R.drawable.init_background,
                "Драсте!"
            )
        )
        data.add(
            StoriesModel(
                "История",
                R.drawable.story_gradient_2,
                R.drawable.init_background,
                "Драсте!"
            )
        )
        data.add(
            StoriesModel(
                "История",
                R.drawable.story_gradient_3,
                R.drawable.init_background,
                "Драсте!"
            )
        )

        return data
    }
}