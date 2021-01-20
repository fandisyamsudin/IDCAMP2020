package com.idcamp2020.made.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.idcamp2020.made.core.domain.model.Movie

class DiffUtils(private val oldList: List<Movie>,
    private val newList: List<Movie>)
    : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val(_,
            voteAverage,
            title,
            releaseDate,
            overview,
            posterPath,
            backdropPath,
            isFavorite) = oldList[oldItemPosition]

        val(_,
            voteAverageNew,
            titleNew,
            releaseDateNew,
            overviewNew,
            posterPathNew,
            backdropPathNew,
            isFavoriteNew) = newList[newItemPosition]

        return voteAverage == voteAverageNew
                && title == titleNew
                && releaseDate == releaseDateNew
                && overview == overviewNew
                && posterPath == posterPathNew
                && backdropPath == backdropPathNew
                && isFavorite == isFavoriteNew
    }
}