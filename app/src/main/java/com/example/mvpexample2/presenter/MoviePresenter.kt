package com.example.mvpexample2.presenter

import com.example.mvpexample2.model.MainInteract
import com.example.mvpexample2.model.Movie
import com.example.mvpexample2.view.MainView

class MoviePresenter(
    private var mainView: MainView,
    private val interactor: MainInteract
): MainInteract.OnFinishedListener {
    override fun onResultSuccess(movies: List<Movie>) {
        mainView.hideProgress()
        mainView.setData(movies)
    }

    override fun onResultFailed(msg: String) {
        mainView.hideProgress()
        mainView.setError(msg)
    }

    fun getAllMovies() {
        mainView.showProgress()
        interactor.getAllMovies(this)
    }
}



