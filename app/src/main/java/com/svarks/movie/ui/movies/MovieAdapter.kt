package com.svarks.movie.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.svarks.movie.databinding.ItemMovieBinding
import com.svarks.movie.datamodel.Result

/**
 * Movie Adapter class to display the list of movies
 */
class MovieAdapter(
    private val movies: List<Result>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    /**
     * @param binding
     * Inner class to bind the data
     */
    inner class MovieHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.llMovie.setOnClickListener(this)
        }
        lateinit var data: Result
        fun bind(movieContent: Result) {
            data = movieContent
            binding.tvTitle.text = movieContent.displayTitle
            Glide.with(binding.root.context)
                .load(movieContent.multimedia.src)
                .into(binding.imgMovie)
        }

        override fun onClick(view: View?) {
            listener.onItemClick(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return if (movies.isNotEmpty()) movies.size else 0
    }

    interface OnItemClickListener {
        fun onItemClick(data: Result)
    }
}
