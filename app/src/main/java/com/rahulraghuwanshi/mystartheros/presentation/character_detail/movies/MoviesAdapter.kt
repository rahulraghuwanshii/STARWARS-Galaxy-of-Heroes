package com.rahulraghuwanshi.mystartheros.presentation.character_detail.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rahulraghuwanshi.mystartheros.data.network.model.MoviesResponse
import com.rahulraghuwanshi.mystartheros.databinding.ItemMoviesBinding

class MoviesAdapter :
    ListAdapter<MoviesResponse, MoviesAdapter.MoviesViewHolder>(CHARACTER_COMPARATOR) {

    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(moviesResponse: MoviesResponse?) {
            binding.tvMovieName.text = moviesResponse?.title
            binding.tvDescription.text = moviesResponse?.openingCrawl
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<MoviesResponse>() {
            override fun areItemsTheSame(
                oldItem: MoviesResponse,
                newItem: MoviesResponse
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: MoviesResponse,
                newItem: MoviesResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}