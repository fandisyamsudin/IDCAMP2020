package com.jetpack.module012.detail

import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.Movie
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.utils.DataDummy

class DetailViewModel: ViewModel() {
    private var id: Int = 0

    fun selectDetailMovie(movieId: Int){
        this.id = movieId
    }

    fun selectDetailTV(tvId: Int){
        this.id = tvId
    }

    fun observeDetailMovies(): Movie? {
        var data: Movie? = null
        for (movies in DataDummy.dummyMovie()) {
            if (movies.id == id) {
                data = movies
            }
        }
        return data
    }

    fun observeDetailTV(): TVShow? {
        var data: TVShow? = null
        for (tv in DataDummy.dummyTvShow()) {
            if (tv.id == id) {
                data = tv
            }
        }
        return data
    }
}