package com.ravimhzn.starwars.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.utils.CustomResource
import com.ravimhzn.starwars.utils.DataManager
import javax.inject.Inject

class FilmDetailViewModel @Inject constructor(private val dataManager: DataManager) : ViewModel() {

    //We can use injected DataManager and retrieve film data from this function
    fun getFilmData(): LiveData<CustomResource<Film>> {
        return dataManager.getCachedData()
    }
}