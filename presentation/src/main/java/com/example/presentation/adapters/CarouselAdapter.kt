package com.example.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.GamesModelData
import com.example.presentation.R


class CarouselAdapter(val context: Context) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    private var games = listOf<GamesModelData>()

    fun submitList(newList: List<GamesModelData>) {
        games = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_game_genre, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size

    class CarouselViewHolder(private val binding: View) :
        RecyclerView.ViewHolder(binding) {

        fun bind(game: GamesModelData) {
            var gameGenreTitle: TextView = itemView.findViewById<TextView>(com.example.presentation.R.id.tvGenreName)
            gameGenreTitle.text = game.name
        }
    }

}
