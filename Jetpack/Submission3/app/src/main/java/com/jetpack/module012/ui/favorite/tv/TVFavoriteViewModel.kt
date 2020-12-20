package com.jetpack.module012.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.TVShowEntity
import javax.inject.Inject

class TVFavoriteViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {
    fun getFavoriteTV(): LiveData<PagedList<TVShowEntity>> = catalogRepository.getFavoriteTV()
}