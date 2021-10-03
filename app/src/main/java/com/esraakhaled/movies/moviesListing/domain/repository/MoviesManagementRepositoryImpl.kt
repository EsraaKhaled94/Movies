package com.esraakhaled.movies.moviesListing.domain.repository

import com.esraakhaled.movies.BuildConfig
import com.esraakhaled.movies.base.network.BaseNetwork
import com.esraakhaled.movies.moviesListing.data.model.ListMoviesResponse
import com.esraakhaled.movies.moviesListing.data.network.ApiManagement
import com.esraakhaled.movies.moviesListing.data.repository.MoviesManagementRepository

object MoviesManagementRepositoryImpl : MoviesManagementRepository {
    private val cloud: BaseNetwork? by lazy {
        BaseNetwork.getInstance()
    }

    override suspend fun listVideos(page: Int): ListMoviesResponse {
        return cloud?.create(ApiManagement::class.java)?.listMovies(
            BuildConfig.API_KEY, page
        )!!
    }

}
