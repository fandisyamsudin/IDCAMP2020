package com.idcamp2020.made.di

import com.idcamp2020.made.core.domain.usecase.MovieInteractor
import com.idcamp2020.made.core.domain.usecase.MovieUseCase
import com.idcamp2020.made.ui.detail.DetailViewModel
import com.idcamp2020.made.ui.home.HomeViewModel
import com.idcamp2020.made.ui.home.SearchViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

@FlowPreview
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}