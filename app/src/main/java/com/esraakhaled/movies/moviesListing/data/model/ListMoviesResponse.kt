package com.esraakhaled.movies.moviesListing.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListMoviesResponse {
    @SerializedName("page")
    @Expose
    var page = 0

    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages = 0

    @SerializedName("total_results")
    @Expose
    var totalResults = 0
}