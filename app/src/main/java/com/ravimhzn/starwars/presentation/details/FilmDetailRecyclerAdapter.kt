package com.ravimhzn.starwars.presentation.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.starwars.R
import com.ravimhzn.starwars.models.Characters
import com.ravimhzn.starwars.utils.RecyclerViewListener
import kotlinx.android.synthetic.main.custom_character_layout.view.*

class FilmDetailRecyclerAdapter :
    RecyclerView.Adapter<FilmDetailRecyclerAdapter.CharactersViewHolder>() {

    private var c: List<Characters> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_character_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return c.size
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(c[position])
    }

    fun setCharacters(c: List<Characters>) {
        this.c = c
        notifyDataSetChanged()
    }


    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Holds the TextView that will add each animal to
        val tvCharacters = itemView.tvCharacters

        fun bind(
            c: Characters
        ) {
            tvCharacters.text = c.name
        }

    }

}