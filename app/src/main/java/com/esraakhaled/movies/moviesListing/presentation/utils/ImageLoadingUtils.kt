package com.esraakhaled.movies.moviesListing.presentation.utils

import android.widget.ImageView
import com.esraakhaled.movies.R
import com.squareup.picasso.Picasso

object ImageLoadingUtils {
    fun loadImage(movieImage: ImageView, posterFullPath: String) {
        Picasso.get()
            .load(posterFullPath)
            .placeholder(R.color.gray_d0d0d0)
            .fit()
            .into(movieImage)
    }

}
