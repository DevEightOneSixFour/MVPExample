package com.example.mvpexample2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpexample2.R
import com.example.mvpexample2.databinding.ActivityMainBinding
import com.example.mvpexample2.databinding.MovieShomovieLayoutBinding
import com.example.mvpexample2.model.MainInteract
import com.example.mvpexample2.model.Movie
import com.example.mvpexample2.presenter.MoviePresenter
import com.example.mvpexample2.view.adapter.MovieAdapter

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var moviePresenter: MoviePresenter
    private lateinit var movieAdapter: MovieAdapter

    // viewBinding
    // pattern to prevent memory leaks, LeakCanary......
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter()
        moviePresenter = MoviePresenter(this, MainInteract())
        moviePresenter.getAllMovies()

        // old way here
        val viewName = findViewById<TextView>(R.id.tv_error_text)

    }

    override fun showProgress() {
        binding.pbLoading.visibility = View.VISIBLE
        binding.rvMovies.visibility = View.GONE
        binding.tvErrorText.visibility = View.GONE
    }

    override fun hideProgress() {
        binding.pbLoading.visibility = View.GONE
    }

    override fun setData(movies: List<Movie>) {
        binding.rvMovies.apply {
            movieAdapter.setMovieList(movies)
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            visibility = View.VISIBLE
        }
    }

    override fun setError(msg: String) {
        // scope functions
//        binding.tvErrorText.text = msg
//        binding.tvErrorText.visibility = View.VISIBLE

        binding.tvErrorText.apply {
            text = msg
            visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

// Interfaces
// Contract between the View Layer and Presenter Layer
interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setData(movies: List<Movie>)
    fun setError(msg: String)
}