package com.ravimhzn.starwars.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.network.FilmAPI
import com.ravimhzn.starwars.repositories.main.FilmListRepo
import com.ravimhzn.starwars.utils.Resources
import com.util.LiveDataTestUtil
import io.reactivex.Flowable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File


class FilmListRepoTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    lateinit var filmApi: FilmAPI

    lateinit var filmListRepo: FilmListRepo

    @Before
    fun init() {
        filmApi = mock()
        filmListRepo = FilmListRepo(filmApi)
    }

    @Test
    fun `filmListRepoPerformBackgroundOperation return title`() {
        //LiveData<Resources<Film>>
        //Flowable<Film>

        val film = Film(title = "AAAA")
        var expectedData: Flowable<Film> = Flowable.just(film)

        whenever(filmApi.getFilms()).thenReturn(expectedData)

        val liveData = filmListRepo.filmListRepoPerformBackgroundOperation()
        val result = LiveDataTestUtil.getValue<Resources<Film>>(liveData)
        assertEquals("AAAA", (result as Resources.Success).data?.title)
    }

    @Test
    fun `filmListRepoPerformBackgroundOperation return list of results`() {
        val path = "src/test/java/com/ravimhzn/starwars/resources/films.json"
        val file = File(path)
        val a = file.inputStream().readBytes().toString(Charsets.UTF_8)
        //print(a)
        val gson = Gson()
        val film = gson.fromJson(a, Film::class.java)
        var expectedData: Flowable<Film> = Flowable.just(film)
        whenever(filmApi.getFilms()).thenReturn(expectedData)
        val liveData = filmListRepo.filmListRepoPerformBackgroundOperation()
        val result = LiveDataTestUtil.getValue<Resources<Film>>(liveData)
        assertEquals(3, (result as Resources.Success).data?.results?.size)
    }
}
