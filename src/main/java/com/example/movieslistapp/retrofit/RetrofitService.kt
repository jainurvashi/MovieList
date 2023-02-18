package com.example.movieslistapp.retrofit

import com.example.movieslistapp.model.MovieModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("movie/top_rated")
    suspend fun getAllTopRatedMovies(@Query(value = "api_key") apiKey :String, @Query("page")  currentPage:Int,
        @Query("language")  language:String) : Response<MovieModel>
    @GET("movie/popular")
    suspend fun getAllPopularMovies(@Query(value = "api_key") apiKey :String, @Query("page")  currentPage:Int,
                                    @Query("language")  language:String) : Response<MovieModel>
    @GET("movie/top_rated")
    suspend fun getAllLatestMovies(@Query(value = "api_key") apiKey :String, @Query("page")  currentPage:Int,
                                   @Query("language")  language:String) : Response<MovieModel>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(baseUrl: String) : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}