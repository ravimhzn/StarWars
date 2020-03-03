package com.ravimhzn.starwars.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.repositories.main.FilmListRepo
import com.ravimhzn.starwars.utils.DataManager
import com.ravimhzn.starwars.utils.Resources
import javax.inject.Inject

class FilmListViewModel @Inject constructor(
    private val filmListRepo: FilmListRepo,
    private val dataManager: DataManager
) : ViewModel() {

    private val TAG = FilmListViewModel::class.java.name

    /**
     * Since viewmodel injection is not directly supported by Dagger,
     * we have to create ViewModelProviderFactory, after which we have to define this viewmodel
     * i.e. FilmListViewModel inside FilmListViewModelModule and then declare it as module inside
     * ActivityBuildersModule. That we we will be able to inject this viewmodel into our activity.
     */


    /**
     * Using DataManager class to handle same data in multiple activities/ fragments.
     */
    fun getMoviesFromServer() {
        dataManager.getMoviesFromDataManager(filmListRepo.filmListRepoPerformBackgroundOperation())
    }

    fun getMovies(): LiveData<Resources<Film>> {
        return dataManager.getCachedData()
    }
}
