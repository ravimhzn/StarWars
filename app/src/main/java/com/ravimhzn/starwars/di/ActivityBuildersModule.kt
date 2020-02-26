package com.ravimhzn.starwars.di


import com.ravimhzn.starwars.di.presentation.details.FilmDetailModule
import com.ravimhzn.starwars.di.presentation.details.FilmDetailViewModelModule
import com.ravimhzn.starwars.di.presentation.main.FilmListViewModelModule
import com.ravimhzn.starwars.di.presentation.main.MainModule
import com.ravimhzn.starwars.presentation.details.FilmDetail
import com.ravimhzn.starwars.presentation.main.FilmList
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

    @ContributesAndroidInjector(
        modules = [
            FilmDetailModule::class,
            FilmDetailViewModelModule::class
        ]
    )
    abstract fun contributeFilmDetail(): FilmDetail
}
