package com.ravimhzn.starwars.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.starwars.R
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.utils.CustomResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FilmList : DaggerAppCompatActivity() {

    private val TAG = FilmList::class.java.name

    @Inject
    lateinit var TEST: String

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    lateinit var viewModel: FilmListViewModel

    lateinit var progressBar: ProgressBar

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, TEST)
        initViews()
        initiateViewModel()
        subscribeToObservers()
        viewModel.getMoviesFromServer()
    }


    private fun initViews() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun initiateViewModel() {
        viewModel =
            ViewModelProvider(this, providerFactory).get(FilmListViewModel::class.java)
    }

    private fun subscribeToObservers() {
        viewModel.getMovies().observe(this,
            Observer<CustomResource<Film>> { t ->
                when (t) {
                    is CustomResource.Loading -> {
                        showProgressBar(true)
                    }
                    is CustomResource.Error -> {
                        showProgressBar(false)
                    }
                    is CustomResource.Success -> {
                        showProgressBar(false)
                        Log.d(TAG, t.data?.results?.get(0)?.director)
                    }
                }
            })
    }

    private fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
