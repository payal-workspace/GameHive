package com.example.presentation.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.SportsModelData
import com.example.presentation.R

class CarouselAdapter :
    ListAdapter<SportsModelData, CarouselAdapter.CarouselViewHolder>(GameDiffCallback()) {

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gameImage: ImageView = itemView.findViewById(R.id.gameImage)
        private val gameTitle: TextView = itemView.findViewById(R.id.gameTitle)

        fun bind(game: SportsModelData) {
            gameTitle.text = game.name
            gameImage.setImageResource(game.gamePosterImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_genre, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class GameDiffCallback : DiffUtil.ItemCallback<SportsModelData>() {
    override fun areItemsTheSame(oldItem: SportsModelData, newItem: SportsModelData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SportsModelData, newItem: SportsModelData): Boolean {
        return oldItem == newItem
    }
}


