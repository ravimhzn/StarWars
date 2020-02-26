package com.ravimhzn.starwars.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Characters(
    @SerializedName("count") var count: Int,
    @SerializedName("results") var results: List<Characters>?,
    @SerializedName("name") var name: String,
    @SerializedName("hair_color") var hair_color: String,
    @SerializedName("skin_color") var skin_color: String,
    @SerializedName("eyeColor") var eyeColor: String,
    @SerializedName("height") var height: String,
    @SerializedName("birth_year") var birth_year: String
) : Parcelable {
    //Using blank constructor for our CustomResource Class
    constructor() : this(
        0,
        null,
        "",
        "",
        "",
        "",
        "",
        ""
    )
}

