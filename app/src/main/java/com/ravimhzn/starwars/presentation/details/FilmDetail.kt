package com.ravimhzn.starwars.presentation.details

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravimhzn.starwars.R
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.utils.Constants.Companion.POS
import com.ravimhzn.starwars.utils.CustomResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FilmDetail : DaggerAppCompatActivity() {

    var TAG = FilmDetail::class.java.name

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    lateinit var viewModel: FilmDetailViewModel

    var positon: Int = 0

    lateinit var tvReleaseDate: TextView
    lateinit var tvDirector: TextView
    lateinit var tvProducer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        intent.extras?.getInt(POS).let {
            positon = it!!
        }
        initViews()
        initViewModel()
        subscribeObservers()
    }

    private fun initViews() {
        tvReleaseDate = findViewById(R.id.tvReleaseDate)
        tvDirector = findViewById(R.id.tvDirector)
        tvProducer = findViewById(R.id.tvProducer)
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this, providerFactory).get(FilmDetailViewModel::class.java)
    }

    private fun subscribeObservers() {
        viewModel.getFilmData().observe(this,
            Observer<CustomResource<Film>> { t ->
                when (t) {
                    is CustomResource.Error -> {
                        Log.d(TAG, t.message)
                    }
                    is CustomResource.Success -> {
                        t.data?.let {
                            it.results?.let { it1 ->
                                // Log.d(TAG, it1[positon].director)
                                setUpViews(it1)
                            }
                        }
                    }
                }
            })
    }

    private fun setUpViews(list: List<Film>) {
        tvReleaseDate.text = list[positon].release_date
        tvDirector.text = list[positon].director
        tvProducer.text = list[positon].producer
    }
}
