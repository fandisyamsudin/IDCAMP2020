package com.idcamp2020.made.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idcamp2020.made.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    @ExperimentalCoroutinesApi
    private val queryBroadcastChannel = ConflatedBroadcastChannel<String>()

    @ExperimentalCoroutinesApi
    fun setQuerySearch(query: String) {
        queryBroadcastChannel.offer(query)
    }

    @ExperimentalCoroutinesApi
    val resultSearch = queryBroadcastChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            movieUseCase.getMovieSearch(it)
        }.asLiveData()
}