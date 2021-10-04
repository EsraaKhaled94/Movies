package com.esraakhaled.movies.moviesListing.presentation.utils

import com.esraakhaled.movies.moviesListing.data.model.Movie

interface OnMovieClickListener {
    fun onMovieClicked(movie: Movie)
}