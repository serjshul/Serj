package com.serjrecommend.places


class PlacesModel(private var name: String, private var location: String,
                  private var photo: Int, private var address: String,
                  private var description: String) {

    fun getName(): String = name

    fun setName(newName: String) {
        this.name = newName
    }

    fun getPlaceLocation(): String = location

    fun setPlaceLocation(newLocation: String) {
        this.location = newLocation
    }

    fun getPhoto(): Int = photo

    fun setPhoto(newPhoto: Int) {
        this.photo = newPhoto
    }

    fun getAddress(): String = address

    fun setAddress(newAddress: String) {
        this.address = newAddress
    }

    fun getDescription(): String = description

    fun setDescription(newDescription: String) {
        this.description = newDescription
    }
}