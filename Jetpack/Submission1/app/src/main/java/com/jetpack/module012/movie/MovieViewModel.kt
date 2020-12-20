package com.jetpack.module012.movie

import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.Movie
import com.jetpack.module012.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun observeMovie(): ArrayList<Movie>{
        return DataDummy.dummyMovie()
    }
}