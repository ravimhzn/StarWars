package com.ravimhzn.starwars.di.presentation.details

import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.di.ViewModelKey
import com.ravimhzn.starwars.presentation.details.FilmDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FilmDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilmDetailViewModel::class)
    abstract fun bindFilmViewModel(filmDetailViewModel: FilmDetailViewModel): ViewModel
}