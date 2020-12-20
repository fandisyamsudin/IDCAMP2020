package com.jetpack.module012.di

import com.jetpack.module012.ui.favorite.movie.MovieFavoriteFragment
import com.jetpack.module012.ui.favorite.tv.TVFavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFavoriteFragment(): MovieFavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeTVFavoriteFragment(): TVFavoriteFragment
}