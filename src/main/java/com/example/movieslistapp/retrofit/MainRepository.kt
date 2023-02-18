package com.example.movieslistapp.retrofit

import com.example.movieslistapp.model.MovieModel


class MainRepository constructor(private val retrofitService: RetrofitService) {
    val apiKey = "909594533c98883408adef5d56143539"
    val lnguage ="en-US"

    suspend fun getAllTopRatedMovies( pageNumber:Int) : NetworkState<MovieModel> {

        val response = retrofitService.getAllTopRatedMovies(apiKey,pageNumber, lnguage )
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
    suspend fun getAllPopularMovies(pageNumber:Int) : NetworkState<MovieModel> {

        val response = retrofitService.getAllPopularMovies(apiKey,pageNumber, lnguage )
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
    suspend fun getAllLatestMovies(pageNumber:Int) : NetworkState<MovieModel> {

        val response = retrofitService.getAllLatestMovies(apiKey,pageNumber, lnguage)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}