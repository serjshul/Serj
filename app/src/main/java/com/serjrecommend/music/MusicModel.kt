package com.serjrecommend.music

class MusicModel(private var MusicName: String, private var MusicCoverId: Int,
                 private var MusicColor: String, private var MusicVideoId: Int) {

    fun getName(): String = MusicName

    fun setName(newMusicName: String) {
        this.MusicName = newMusicName
    }

    fun getImageId(): Int = MusicCoverId

    fun setImageId(newMusicCoverId: Int) {
        this.MusicCoverId = newMusicCoverId
    }

    fun getColor(): String = MusicColor

    fun setColor(newMusicColor: String) {
        this.MusicColor = newMusicColor
    }

    fun getVideoId(): Int = MusicVideoId

    fun setVideo(newMusicVideoId: Int) {
        this.MusicVideoId = newMusicVideoId
    }
}