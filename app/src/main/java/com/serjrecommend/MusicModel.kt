package com.serjrecommend

class MusicModel(var course_name: String, var imgid: Int) {

    fun getMusicName(): String {
        return course_name
    }

    fun setMusicName(course_name: String) {
        this.course_name = course_name
    }

    fun getMusicImageId(): Int {
        return imgid
    }

    fun setMusicImageId(imgid: Int) {
        this.imgid = imgid
    }
}