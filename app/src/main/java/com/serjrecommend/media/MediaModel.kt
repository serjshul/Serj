package com.serjrecommend.media


class MediaModel(private var coverId: Int, private var decription: String) {

    fun getCoverId(): Int = coverId

    fun setCoverId(newCoverId: Int) {
        this.coverId = newCoverId
    }

    fun getDescription(): String = decription

    fun setDescription(newDescription: String) {
        this.decription = newDescription
    }
}