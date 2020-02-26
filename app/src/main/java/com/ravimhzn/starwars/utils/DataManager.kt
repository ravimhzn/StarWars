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
    var cachedData = MediatorLiveData<CustomResource<Film>>()

    fun getMoviesFromDataManager(source: LiveData<CustomResource<Film>>) {
        Log.d(TAG, "Inside Datamanager, getMoviesFromDataManager")
        cachedData.value = CustomResource.Loading(null) //start the progressing bar
        cachedData.addSource(source) { t ->
            cachedData.value = t
            cachedData.removeSource(source)
        }
    }

    fun getCachedData(): LiveData<CustomResource<Film>> {
        return cachedData
    }
}