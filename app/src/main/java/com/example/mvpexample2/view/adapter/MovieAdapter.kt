package com.example.mvpexample2.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpexample2.databinding.MovieListItemBinding
import com.example.mvpexample2.model.Movie

class MovieAdapter(
    private val movies: MutableList<Movie> = mutableListOf()
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    fun setMovieList(newList: List<Movie>) {
        movies.addAll(newList)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val binding: MovieListItemBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun onBind(movie: Movie) {
                binding.tvMovieTitle.text = movie.name
                Glide.with(binding.ivMoviePoster)
                    .load(movie.imageUrl)
                    .into(binding.ivMoviePoster)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount() = movies.size
}