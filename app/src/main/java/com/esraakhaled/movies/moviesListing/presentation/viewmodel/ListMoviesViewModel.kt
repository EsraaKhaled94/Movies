package com.esraakhaled.movies.moviesListing.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.esraakhaled.movies.base.presentation.viewmodel.BaseViewModel
import com.esraakhaled.movies.moviesListing.data.model.Movie
import com.esraakhaled.movies.moviesListing.domain.interactor.ListVideosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListMoviesViewModel : BaseViewModel() {
    private val moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesListObservable: LiveData<List<Movie>> = moviesList
    private val movies: ArrayList<Movie> = ArrayList()

    private var totalData: Int = -1
    private var pageNumber = 1

    private val listVideosUseCase: ListVideosUseCase = ListVideosUseCase()
    fun listMovies(itemCount: Int) {
        if (itemCount < totalData || totalData == -1) {
            addLoadingValue(true)
            viewModelScope.launch(Dispatchers.IO + handler) {
                val results = listVideosUseCase.build(pageNumber);
                movies.addAll(results.results!!)
                moviesList.postValue(movies)
                totalData = results.totalResults
                pageNumber++

                addLoadingValue(false)
            }
        }
    }
}