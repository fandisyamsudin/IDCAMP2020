package com.bfaa.idchamp2020.di.ui.main

import androidx.lifecycle.ViewModel
import com.bfaa.idchamp2020.helper.ViewModelKey
import com.bfaa.idchamp2020.ui.main.MainViewModel
import com.bfaa.idchamp2020.ui.main.detail.DetailViewModel
import com.bfaa.idchamp2020.ui.main.favorite.FavoriteViewModel
import com.bfaa.idchamp2020.ui.main.follow.ProfileFollowViewModel
import com.bfaa.idchamp2020.ui.main.home.HomeViewModel
import com.bfaa.idchamp2020.ui.main.setting.SettingViewModel
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


    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(settingViewModel: SettingViewModel): ViewModel
}