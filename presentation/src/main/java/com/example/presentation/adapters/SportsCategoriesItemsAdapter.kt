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
import com.example.presentation.databinding.GamesTitleItemBinding

class SportsCategoryItemAdapter : ListAdapter<SportsModelLists, SportsCategoryItemAdapter.ViewHolder>(SportsCategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GamesTitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: GamesTitleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SportsModelLists) {
            binding.gameImage.setBackgroundResource(item.img_sports_type_item)
            binding.tvCategoryName.text = item.game_title
        }
    }

    class SportsCategoryDiffCallback : DiffUtil.ItemCallback<SportsModelLists>() {
        override fun areItemsTheSame(oldItem: SportsModelLists, newItem: SportsModelLists): Boolean {
            return oldItem.game_title == newItem.game_title
        }

        override fun areContentsTheSame(oldItem: SportsModelLists, newItem: SportsModelLists): Boolean {
            return oldItem == newItem
        }
    }
}



