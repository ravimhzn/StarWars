package com.ravimhzn.starwars.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.starwars.R
import com.ravimhzn.starwars.models.Film
import com.ravimhzn.starwars.utils.RecyclerViewListener
import kotlinx.android.synthetic.main.custom_layout.view.*

class FilmRecyclerAdapter :
    RecyclerView.Adapter<FilmRecyclerAdapter.FilmViewHolder>() {

    private var films: List<Film> = ArrayList()
    lateinit var recyclerViewListener: RecyclerViewListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(films[position], this.recyclerViewListener)
    }

    fun setFilm(films: List<Film>, recyclerViewListener: RecyclerViewListener) {
        this.films = films
        this.recyclerViewListener = recyclerViewListener
        notifyDataSetChanged()
    }


    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Holds the TextView that will add each animal to
        val tvMovie = itemView.tvMovie
        val tvReleaseDate = itemView.tvReleaseDate

        fun bind(
            film: Film,
            recyclerViewListener: RecyclerViewListener
        ) {
            tvMovie.text = film.title
            tvReleaseDate.text = "Released Date: ${film.release_date}"
            itemView.setOnClickListener(View.OnClickListener {
                recyclerViewListener.OnRecyclerViewClicked(adapterPosition)
            })
        }

    }

}