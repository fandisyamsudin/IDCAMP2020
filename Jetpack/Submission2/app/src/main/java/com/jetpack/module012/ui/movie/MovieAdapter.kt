package com.jetpack.module012.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.module012.R
import com.jetpack.module012.data.Movie
import com.jetpack.module012.databinding.ItemListBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private var listMovie = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        listMovie.clear()
        listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_list,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(movie: Movie)
    }

    inner class MovieViewHolder(val binding: ItemListBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(movie)
            }
        }
    }
}