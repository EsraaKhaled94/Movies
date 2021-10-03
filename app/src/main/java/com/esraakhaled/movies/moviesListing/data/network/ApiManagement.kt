package com.esraakhaled.movies.moviesListing.data.network

import com.esraakhaled.movies.moviesListing.data.model.ListMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiManagement {
    @GET("movie/popular")
    suspend fun listMovies(@Query("api_key") apiKey: String,@Query("page") page: Int): ListMoviesResponse
}
