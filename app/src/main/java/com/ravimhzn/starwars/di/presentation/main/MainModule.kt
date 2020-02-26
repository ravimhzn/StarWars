package com.ravimhzn.starwars.di.presentation.main

import com.ravimhzn.starwars.network.FilmAPI
import com.ravimhzn.starwars.presentation.main.FilmRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideFilmApi(retrofit: Retrofit): FilmAPI {
        return retrofit.create(FilmAPI::class.java)
    }

    @Provides
    fun provideFilmAdapter(): FilmRecyclerAdapter {
        return FilmRecyclerAdapter()
    }
}