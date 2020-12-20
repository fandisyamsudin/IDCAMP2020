package com.bfaa.favoriteapp.di.ui.main

import androidx.lifecycle.ViewModel
import com.bfaa.favoriteapp.helper.ViewModelKey
import com.bfaa.favoriteapp.ui.main.MainViewModel
import com.bfaa.favoriteapp.ui.main.detail.DetailViewModel
import com.bfaa.favoriteapp.ui.main.follow.ProfileFollowViewModel
import com.bfaa.favoriteapp.ui.main.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileFollowViewModel::class)
    abstract fun bindProfileFollowViewModel(profileFollowViewModel: ProfileFollowViewModel): ViewModel
}