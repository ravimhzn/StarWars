package com.ravimhzn.starwars.presentation.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.starwars.R
import com.ravimhzn.starwars.models.Characters
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.utils.Constants.Companion.POS
import com.ravimhzn.starwars.utils.Resources
import com.ravimhzn.starwars.utils.DividerItemDecoration
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class FilmDetail : DaggerAppCompatActivity() {

    var TAG = FilmDetail::class.java.name

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    lateinit var viewModel: FilmDetailViewModel

    @Inject
    lateinit var filmDetailRecyclerAdapter: FilmDetailRecyclerAdapter

    var positon: Int = 0

    lateinit var tvReleaseDate: TextView
    lateinit var tvDirector: TextView
    lateinit var tvProducer: TextView
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        intent.extras?.getInt(POS).let {
            positon = it!!
        }
        initViews()
        initRecyclerView()
        initViewModel()
        subscribeObservers()
        subscribeObserversForCharacters(positon)
    }

    private fun initViews() {
        tvReleaseDate = findViewById(R.id.tvReleaseDate)
        tvDirector = findViewById(R.id.tvDirector)
        tvProducer = findViewById(R.id.tvProducer)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerPeople)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.divider
                )
            )
        )
        recyclerView.adapter = filmDetailRecyclerAdapter
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider(this, providerFactory).get(FilmDetailViewModel::class.java)
    }

    private fun subscribeObservers() {
        viewModel.getFilmData().observe(this,
            Observer<Resources<Film>> { t ->
                when (t) {
                    is Resources.Error -> {
                        Log.d(TAG, t.message)
                    }
                    is Resources.Success -> {
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

    private fun subscribeObserversForCharacters(positon: Int) {
        viewModel.getCharactersFromServer(positon).observe(this,
            Observer<Resources<Characters>> { t ->
                when (t) {
                    is Resources.Loading -> {
                        showProgressBar(true)
                    }
                    is Resources.Error -> {
                        showProgressBar(false)
                        Log.d(TAG, t.message)
                    }
                    is Resources.Success -> {
                        showProgressBar(false)
                        Log.d(TAG, t.data?.results?.get(0)?.name)
                        t.data?.let {
                            it.results?.let { it1 ->
                                filmDetailRecyclerAdapter.setCharacters(it1)
                            }
                        }
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
