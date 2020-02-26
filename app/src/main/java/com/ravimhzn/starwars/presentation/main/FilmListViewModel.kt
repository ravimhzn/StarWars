package com.ravimhzn.starwars.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.network.FilmAPI
import com.ravimhzn.starwars.utils.CustomResource
import com.ravimhzn.starwars.utils.DataManager
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmListViewModel @Inject constructor(
    private val filmAPI: FilmAPI,
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
        dataManager.getMoviesFromDataManager(performBackgroundOperation())
    }

    private fun performBackgroundOperation(): LiveData<CustomResource<Film>> {
        return LiveDataReactiveStreams.fromPublisher(
            filmAPI.getFilms()
                .onErrorReturn {
                    Log.d(TAG, "Error $it")
                    val user = Film()
                    user.count = -1
                    user

                }
                .map(object : Function<Film, CustomResource<Film>> {
                    override fun apply(film: Film): CustomResource<Film> {
                        if (film.count == -1) {
                            return CustomResource.Error("Could not authenticated", null)
                        }
                        return CustomResource.Success(film)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun getMovies(): LiveData<CustomResource<Film>> {
        return dataManager.getCachedData()
    }
}