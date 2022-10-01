package com.example.flixster

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id

/**
 * [RecyclerView.Adapter] that can display a [Movies] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MoviesRecyclerViewAdapter(
    private val movies: List<Movies>,
    private val mListener: OnListFragmentInteractionListener?

)
    : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view, parent.context)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View, val context: Context) : RecyclerView.ViewHolder(mView) {
        var mItem: Movies? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_name) as TextView
        val mMovieOverview: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMoviePosterUrl: ImageView = mView.findViewById<View>(id.movie_image) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieOverview.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        val orientation = holder.context.resources.configuration.orientation

        holder.mItem = movie
        holder.mMovieTitle.text = movie.name
        holder.mMovieOverview.text = movie.description

        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500/" + movie.landImageUrl)
                .centerInside()
                .into(holder.mMoviePosterUrl)
        }
        else {
            Glide.with(holder.mView)
                .load("https://image.tmdb.org/t/p/w500/" + movie.movieImageUrl)
                .centerInside()
                .into(holder.mMoviePosterUrl)
        }

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}