package com.example.cameraappviews

import android.util.MutableBoolean
import androidx.lifecycle.MutableLiveData

var collectibleList = mutableListOf<Collectibles>()

class Collectibles(
    var collectible: Int,
    var name: String,
    var location: String,
    val id: Int? = collectibleList.size,
    var discovered: MutableLiveData<Boolean>
)