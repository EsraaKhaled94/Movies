package com.esraakhaled.movies.moviesListing.data.repository

import com.esraakhaled.movies.moviesListing.data.model.ListMoviesResponse

interface MoviesManagementRepository {
    suspend fun listVideos(page: Int): ListMoviesResponse
}
