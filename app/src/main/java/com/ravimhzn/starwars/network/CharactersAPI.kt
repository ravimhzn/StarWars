package com.ravimhzn.starwars.network

import com.ravimhzn.starwars.models.Characters
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersAPI {

    @GET("people/")
    fun getCharacters(@Query("episode_id") episode_id: Int): Flowable<Characters>
}