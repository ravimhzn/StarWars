package com.ravimhzn.starwars.presentation.main

import androidx.lifecycle.Observer
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.repositories.main.FilmListRepo
import com.ravimhzn.starwars.utils.DataManager
import com.ravimhzn.starwars.utils.InstantExecutorExtension
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExtendWith(InstantExecutorExtension::class)
class FilmListViewModelTest {

    lateinit var filmListViewModel: FilmListViewModel

    @Mock
    lateinit var filmListRepo: FilmListRepo
    @Mock
    lateinit var dataManager: DataManager

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        filmListViewModel = FilmListViewModel(filmListRepo, dataManager)
    }

    /**
     * performBackgroundOperation should return value
     */
    @Test
    fun `getMoviesFromServer return success`() {


    }

    /**
     * performBackgroundOperation should return value
     */
    fun `filmListRepoPerformBackgroundOperation return failure`() {
        //Given
        //When
        //Then
    }


}
