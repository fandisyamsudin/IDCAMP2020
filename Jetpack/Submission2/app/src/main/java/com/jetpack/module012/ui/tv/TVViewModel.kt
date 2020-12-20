package com.jetpack.module012.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.CatalogRepository
import com.jetpack.module012.data.TVShow
import javax.inject.Inject

class TVViewModel @Inject constructor(private val catalogRepository: CatalogRepository) :
        ViewModel() {

    fun getTVShows(): LiveData<List<TVShow>> = catalogRepository.getTVShow()
}