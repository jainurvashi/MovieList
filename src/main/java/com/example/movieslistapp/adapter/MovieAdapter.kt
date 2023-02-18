package com.example.movieslistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieslistapp.databinding.AdapterMovieBinding
import com.example.movieslistapp.model.MovieModel


class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<com.example.movieslistapp.model.Result>()

    fun setMovies(movies: MovieModel) {
        this.movieList.addAll(movies.results.toMutableList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]
     //   if (ValidationUtil.validateMovie(movie)) {
            holder.binding.name.text = movie.title
            Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500"+movie.poster_path).into(holder.binding.imageview)
       // }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}


class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}
