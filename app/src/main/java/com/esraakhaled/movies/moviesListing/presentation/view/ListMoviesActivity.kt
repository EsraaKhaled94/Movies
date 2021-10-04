package com.esraakhaled.movies.moviesListing.presentation.view

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.esraakhaled.movies.R
import com.esraakhaled.movies.base.presentation.view.BaseActivity
import com.esraakhaled.movies.moviesListing.data.model.Movie
import com.esraakhaled.movies.moviesListing.presentation.adapter.MoviesAdapter
import com.esraakhaled.movies.moviesListing.presentation.utils.OnMovieClickListener
import com.esraakhaled.movies.moviesListing.presentation.viewmodel.ListMoviesViewModel
import kotlinx.android.synthetic.main.activity_list_movies.*

class ListMoviesActivity : BaseActivity<ListMoviesViewModel>() {
    private lateinit var adapter: MoviesAdapter
    private var loading: Boolean = true
    private var pastVisibleItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private val listener: OnMovieClickListener = object : OnMovieClickListener {
        override fun onMovieClicked(movie: Movie) {
            startActivity(MovieDetailsDialog.getStartIntent(this@ListMoviesActivity, movie))
        }

    }

    override fun initView() {
        setupRecyclerView()

        viewModel.moviesListObservable.observe(this@ListMoviesActivity, Observer {
            adapter.clear()
            adapter.addItems(it)
            loading = true
        })

        viewModel.listMovies(adapter.itemCount)
    }

    private fun setupRecyclerView() {
        adapter = MoviesAdapter(listener)
        val layoutManager = LinearLayoutManager(this)
        moviesList.layoutManager = layoutManager
        moviesList.adapter = adapter

        moviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            loading = false
                            viewModel.listMovies(adapter.itemCount)
                        }
                    }
                }
            }
        })
    }

    override fun getLayout(): Int = R.layout.activity_list_movies

    override fun getViewModelClass(): Class<ListMoviesViewModel> = ListMoviesViewModel::class.java

    override fun handleLoading(b: Boolean) {
        if (b) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}