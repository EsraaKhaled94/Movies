package com.esraakhaled.movies.moviesListing.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esraakhaled.movies.R
import com.esraakhaled.movies.moviesListing.data.model.Movie
import com.esraakhaled.movies.moviesListing.presentation.utils.ImageLoadingUtils
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private var movies: ArrayList<Movie> = ArrayList()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.movieName.text = movie.originalTitle
            itemView.movieOverview.text = movie.overview
            ImageLoadingUtils.loadImage(itemView.movieImage, movie.getPosterFullPath())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
    fun clear() {
        movies = ArrayList()
        notifyDataSetChanged()
    }

    fun addItems(items: List<Movie>?) {
        if (items != null) {
            movies.addAll(items)
            notifyDataSetChanged()
        }
    }

}