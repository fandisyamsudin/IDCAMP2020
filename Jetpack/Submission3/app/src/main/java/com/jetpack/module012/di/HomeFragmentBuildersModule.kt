package com.jetpack.module012.di

import com.jetpack.module012.ui.movie.MovieFragment
import com.jetpack.module012.ui.tv.TVFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TVFragment

}