package com.ravimhzn.starwars.di.presentation.main

import com.ravimhzn.starwars.network.FilmAPI
import com.ravimhzn.starwars.presentation.main.FilmRecyclerAdapter
import com.ravimhzn.starwars.repositories.main.FilmListRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    fun provideFilmApi(retrofit: Retrofit): FilmAPI {
        return retrofit.create(FilmAPI::class.java)
    }

    @Provides
    fun provideFilmRepository(filmAPI: FilmAPI): FilmListRepo {
        return FilmListRepo(filmAPI)
    }

    @Provides
    fun provideFilmAdapter(): FilmRecyclerAdapter {
        return FilmRecyclerAdapter()
    }

}
