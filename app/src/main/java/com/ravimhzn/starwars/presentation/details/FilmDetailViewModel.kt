package com.ravimhzn.starwars.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.models.Characters
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.network.CharactersAPI
import com.ravimhzn.starwars.utils.Constants.Companion.HTTP_FAILURE
import com.ravimhzn.starwars.utils.CustomResource
import com.ravimhzn.starwars.utils.DataManager
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FilmDetailViewModel @Inject constructor(
    private val dataManager: DataManager,
    private val charactersAPI: CharactersAPI
) : ViewModel() {

    private val TAG = FilmDetailViewModel::class.java.name


    //We can use injected DataManager and retrieve film data from this function
    fun getFilmData(): LiveData<CustomResource<Film>> {
        return dataManager.getCachedData()
    }

    fun getCharactersFromServer(episode_id: Int): LiveData<CustomResource<Characters>> {
        return LiveDataReactiveStreams.fromPublisher(
            charactersAPI.getCharacters(episode_id)
                .onErrorReturn {
                    Log.d(TAG, "Error $it")
                    val c = Characters()
                    c.count = -1
                    c
                }
                .map(object : Function<Characters, CustomResource<Characters>> {
                    override fun apply(c: Characters): CustomResource<Characters> {
                        if (c.count == -1) {
                            return CustomResource.Error(HTTP_FAILURE, null)
                        }
                        return CustomResource.Success(c)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }
}