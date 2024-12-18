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
import com.example.domain.model.SportsModelLists
import com.example.presentation.R

class SportsCategoriesItemsAdapter :
    ListAdapter<SportsModelLists, SportsCategoriesItemsAdapter.CarouselViewHolder>(SportsCatListsDiffUtil()) {

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategoryName: TextView = itemView.findViewById(R.id.tvCategoryName)

        fun bind(game: SportsModelLists) {
            tvCategoryName.text = game.game_title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.games_title_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SportsCatListsDiffUtil : DiffUtil.ItemCallback<SportsModelLists>() {
    override fun areItemsTheSame(oldItem: SportsModelLists, newItem: SportsModelLists): Boolean {
        return oldItem.game_title == newItem.game_title
    }

    override fun areContentsTheSame(oldItem: SportsModelLists, newItem: SportsModelLists): Boolean {
        return oldItem == newItem
    }
}


