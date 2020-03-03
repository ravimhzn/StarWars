package com.ravimhzn.starwars.presentation.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.models.Characters
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.network.CharactersAPI
import com.ravimhzn.starwars.utils.Constants.Companion.HTTP_FAILURE
import com.ravimhzn.starwars.utils.Resources
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
    fun getFilmData(): LiveData<Resources<Film>> {
        return dataManager.getCachedData()
    }

    fun getCharactersFromServer(episode_id: Int): LiveData<Resources<Characters>> {
        return LiveDataReactiveStreams.fromPublisher(
            charactersAPI.getCharacters(episode_id)
                .onErrorReturn {
                    Log.d(TAG, "Error $it")
                    val c = Characters()
                    c.count = -1
                    c
                }
                .map(object : Function<Characters, Resources<Characters>> {
                    override fun apply(c: Characters): Resources<Characters> {
                        if (c.count == -1) {
                            return Resources.Error(HTTP_FAILURE, null)
                        }
                        return Resources.Success(c)
                    }

                })
                .subscribeOn(Schedulers.io())
        )
    }
}
