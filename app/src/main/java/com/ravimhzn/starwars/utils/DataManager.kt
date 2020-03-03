package com.ravimhzn.starwars.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ravimhzn.starwars.models.Film
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor() {
    private var TAG = DataManager::class.java.name
    var cachedData = MediatorLiveData<Resources<Film>>()

    fun getMoviesFromDataManager(source: LiveData<Resources<Film>>) {
        Log.d(TAG, "Inside Datamanager, getMoviesFromDataManager")
        cachedData.value = Resources.Loading(null) //start the progressing bar
        cachedData.addSource(source) { t ->
            cachedData.value = t
            cachedData.removeSource(source)
        }
    }

    fun getCachedData(): LiveData<Resources<Film>> {
        return cachedData
    }
}
