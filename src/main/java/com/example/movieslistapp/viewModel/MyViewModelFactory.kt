package com.example.movieslistapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieslistapp.retrofit.MainRepository

class MyViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LatestMovieViewModel::class.java)) {
            LatestMovieViewModel(this.repository) as T
        }else  if (modelClass.isAssignableFrom(PopularMovieViewModel::class.java)) {
            PopularMovieViewModel(this.repository) as T
        }else  if (modelClass.isAssignableFrom(TopRatedMovieViewModel::class.java)) {
            TopRatedMovieViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}