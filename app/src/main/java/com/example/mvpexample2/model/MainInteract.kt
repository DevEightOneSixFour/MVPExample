package com.example.mvpexample2.model

import com.example.mvpexample2.api.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteract {

    interface OnFinishedListener {
        fun onResultSuccess(movies: List<Movie>)
        fun onResultFailed(msg: String)
    }

    fun getAllMovies(listener: OnFinishedListener) {
        val service = MovieService.getRetrofit().create(MovieService::class.java)

        // Scope to launch the coroutine
        // Context -> Dispatchers(Main, IO, Default, & Unconfined)
        // Main -> Run on the main thread
        // IO -> input/output, database queries and/or network calls
        // Default -> longer computations
        // Unconfined -> testing
        service.getAllMovies().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                listener.onResultSuccess(response.body()!!)
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                listener.onResultFailed("Network call fail")
            }

        })
    }
}
