package com.ravimhzn.starwars.network

import com.ravimhzn.starwars.models.Film
import io.reactivex.Flowable
import retrofit2.http.GET

interface FilmAPI {

    @GET("films")
    fun getFilms(): Flowable<Film>
}