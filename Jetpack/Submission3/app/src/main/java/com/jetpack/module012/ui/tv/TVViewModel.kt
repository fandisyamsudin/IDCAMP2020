package com.jetpack.module012.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.vo.Resource
import javax.inject.Inject

class TVViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
    ViewModel() {

    fun getTVShows(): LiveData<Resource<PagedList<TVShowEntity>>> = catalogRepository.getTVShow()
}