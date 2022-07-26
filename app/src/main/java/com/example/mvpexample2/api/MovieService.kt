package com.example.mvpexample2.api

import com.example.mvpexample2.model.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieService {

    @GET("movielist.json")
    fun getAllMovies(): Call<List<Movie>>
    // is suspended function
    companion object {
        private var instance: Retrofit? = null

        // https://howtodoandroid.com/movielist.json
        fun getRetrofit(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }
    }
}