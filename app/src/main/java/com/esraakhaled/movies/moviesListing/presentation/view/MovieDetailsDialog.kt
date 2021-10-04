package com.esraakhaled.movies.moviesListing.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.esraakhaled.movies.R
import com.esraakhaled.movies.moviesListing.data.model.Movie
import com.esraakhaled.movies.moviesListing.presentation.utils.ImageLoadingUtils
import kotlinx.android.synthetic.main.custom_details_dialog.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieDetailsDialog : AppCompatActivity() {
    private lateinit var movie: Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_details_dialog)
        movie = intent.extras?.getParcelable(EXTRA_MOVIE)!!

        ImageLoadingUtils.loadImage(movieImage, movie.getPosterFullPath())

        movieName.text = movie.originalTitle
        movieOverview.text = movie.overview
        rating.text = movie.voteAverage.toString()
        voteCount.text = movie.voteCount.toString()
        val v: View = window.decorView
        v.setBackgroundResource(R.drawable.dialog_background)
    }

    companion object {
        private const val EXTRA_MOVIE = "EXTRA_MOVIE"

        fun getStartIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsDialog::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            return intent
        }
    }
}