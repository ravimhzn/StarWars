package com.ravimhzn.starwars.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun testString(): String {
        return "DI Working Correctly"
    }
}