package com.esraakhaled.movies.moviesListing.domain.interactor

import com.esraakhaled.movies.moviesListing.data.model.ListMoviesResponse
import com.esraakhaled.movies.moviesListing.domain.repository.MoviesManagementRepositoryImpl

class ListVideosUseCase {
    suspend fun build(page: Int): ListMoviesResponse {
        return MoviesManagementRepositoryImpl.listVideos(page)
    }
}
