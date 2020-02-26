package com.ravimhzn.starwars.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.starwars.R
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.presentation.details.FilmDetail
import com.ravimhzn.starwars.utils.Constants.Companion.POS
import com.ravimhzn.starwars.utils.CustomResource
import com.ravimhzn.starwars.utils.DividerItemDecoration
import com.ravimhzn.starwars.utils.RecyclerViewListener
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject


class FilmList : DaggerAppCompatActivity(), RecyclerViewListener {

    private val TAG = FilmList::class.java.name

    @Inject
    lateinit var TEST: String

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    lateinit var viewModel: FilmListViewModel

    lateinit var progressBar: ProgressBar

    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var recyclerAdapter: FilmRecyclerAdapter

    private lateinit var filmList: List<Film>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, TEST)
        initViews()
        setUpRecyclerView()
        initiateViewModel()
        subscribeToObservers()
        viewModel.getMoviesFromServer()
    }

    private fun setUpRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.divider
                )
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }


    private fun initViews() {
        progressBar = findViewById(R.id.progressBar)
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
                        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
                    }
                    is CustomResource.Success -> {
                        showProgressBar(false)
                        Log.d(TAG, t.data?.results?.get(0)?.director)
                        t.data?.let {
                            it.results?.let { it1 ->
                                setFilmInRecyclerAdapter(it1)
                            }
                        }
                    }
                }
            })
    }

    /**
     * Sort array as per latest Date and display accordingly
     */
    private fun setFilmInRecyclerAdapter(list: List<Film>) {
        Collections.sort(
            list
        ) { p1, p0 -> p0.release_date.compareTo(p1.release_date) }
        recyclerAdapter.setFilm(list, this)

        for (f in list) {
            Log.d("TAG", f.release_date)
        }
    }


    private fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun OnRecyclerViewClicked(position: Int) {
        //Toast.makeText(this, "POSITION:: $position", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, FilmDetail::class.java)
        intent.putExtra(POS, position)
        startActivity(intent)
    }
}
