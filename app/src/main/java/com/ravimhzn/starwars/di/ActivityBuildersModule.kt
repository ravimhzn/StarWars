package com.ravimhzn.starwars.di


import com.ravimhzn.starwars.di.presentation.FilmListViewModelModule
import com.ravimhzn.starwars.di.presentation.MainModule
import com.ravimhzn.starwars.presentation.FilmList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            FilmListViewModelModule::class
        ]
    )
    abstract fun contributeFilmList(): FilmList
}
