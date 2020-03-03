package com.ravimhzn.starwars.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    @SerializedName("count") var count: Int? = 0,
    @SerializedName("results") var results: List<Film>? = null,
    @SerializedName("title") var title: String? = "",
    @SerializedName("episode_id") var episode_id: String = "",
    @SerializedName("opening_crawl") var opening_crawl: String = "",
    @SerializedName("director") var director: String = "",
    @SerializedName("producer") var producer: String = "",
    @SerializedName("release_date") var release_date: String = ""
) : Parcelable {
//    constructor() : this(
//        0,
//        null,
//        "",
//        "",
//        "",
//        "",
//        "",
//        ""
//    ) //Empty constructor
}

