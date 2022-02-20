package com.example.cameraappviews

var collectibleList = mutableListOf<Collectibles>()

class Collectibles(
    var collectible: Int,
    var name: String,
    var location: String,
    val id: Int? = collectibleList.size,
    val discovered: Boolean
)