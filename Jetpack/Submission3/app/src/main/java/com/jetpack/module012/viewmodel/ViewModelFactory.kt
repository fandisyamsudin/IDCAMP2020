package com.jetpack.module012.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.ui.detail.DetailViewModel
import com.jetpack.module012.ui.favorite.movie.MovieFavoriteViewModel
import com.jetpack.module012.ui.favorite.tv.TVFavoriteViewModel
import com.jetpack.module012.ui.movie.MovieViewModel
import com.jetpack.module012.ui.tv.TVViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mCatalogRepository: CatalogRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TVViewModel::class.java) -> {
                TVViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TVFavoriteViewModel::class.java) -> {
                TVFavoriteViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}