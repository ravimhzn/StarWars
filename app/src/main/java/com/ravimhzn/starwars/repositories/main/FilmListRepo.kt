package com.ravimhzn.starwars.repositories.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.network.FilmAPI
import com.ravimhzn.starwars.utils.Constants
import com.ravimhzn.starwars.utils.Resources
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmListRepo @Inject constructor(private var filmAPI: FilmAPI) {

    private val TAG = FilmListRepo::class.java.name

    fun filmListRepoPerformBackgroundOperation(): LiveData<Resources<Film>> {
        return LiveDataReactiveStreams.fromPublisher(
            filmAPI.getFilms()
                .onErrorReturn {
                    Log.d(TAG, "Error $it")
                    val user = Film()
                    user.count = -1
                    user

                }
                .map(object : Function<Film, Resources<Film>> {
                    override fun apply(film: Film): Resources<Film> {
                        if (film.count == -1) {
                            return Resources.Error(Constants.HTTP_FAILURE, null)
                        }
                        return Resources.Success(film)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }
}
