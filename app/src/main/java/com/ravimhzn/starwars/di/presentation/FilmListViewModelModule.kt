package com.ravimhzn.starwars.di.presentation

import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.di.ViewModelKey
import com.ravimhzn.starwars.presentation.FilmListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FilmListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilmListViewModel::class)
    abstract fun bindAuthViewModel(filmListViewModel: FilmListViewModel): ViewModel
}