package com.serjrecommend.music

class MusicModel(private var name: String, private var coverId: Int,
                 private var color: String, private var videoId: Int) {

    fun getName(): String = name

    fun setName(newName: String) {
        this.name = newName
    }

    fun getImageId(): Int = coverId

    fun setImageId(newCoverId: Int) {
        this.coverId = newCoverId
    }

    fun getColor(): String = color

    fun setColor(newColor: String) {
        this.color = newColor
    }

    fun getVideoId(): Int = videoId

    fun setVideo(newVideoId: Int) {
        this.videoId = newVideoId
    }
}