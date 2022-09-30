package com.example.flixster

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("title")
    var name: String? = null

    @SerializedName("overview")
    var description: String? = null

    @JvmField
    @SerializedName("poster_path")
    var movieImageUrl: String? = null
}

