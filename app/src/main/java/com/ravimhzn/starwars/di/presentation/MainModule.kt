package com.ravimhzn.starwars.di.presentation

import com.ravimhzn.starwars.network.FilmAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    fun provideAuthLoginApi(retrofit: Retrofit): FilmAPI {
        return retrofit.create(FilmAPI::class.java)
    }
}