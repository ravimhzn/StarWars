package com.ravimhzn.starwars.di.presentation.details

import com.ravimhzn.starwars.network.CharactersAPI
import com.ravimhzn.starwars.presentation.details.FilmDetailRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FilmDetailModule {

    @Provides
    fun provieCharactersApi(retrofit: Retrofit): CharactersAPI {
        return retrofit.create(CharactersAPI::class.java)
    }

    @Provides
    fun provideFilmDetailRecyclerAdapter(): FilmDetailRecyclerAdapter {
        return FilmDetailRecyclerAdapter()
    }
}