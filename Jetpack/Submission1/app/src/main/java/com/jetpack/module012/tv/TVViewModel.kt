package com.jetpack.module012.tv

import androidx.lifecycle.ViewModel
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.utils.DataDummy

class TVViewModel : ViewModel() {

    fun observeTV(): ArrayList<TVShow>{
        return DataDummy.dummyTvShow()
    }
}