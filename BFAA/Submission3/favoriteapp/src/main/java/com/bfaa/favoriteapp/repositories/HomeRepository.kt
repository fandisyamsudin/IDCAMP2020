package com.bfaa.favoriteapp.repositories

import android.content.Context
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomeRepository @Inject constructor(
    private val userRepository: UserRepository
) {
    private val compositeDisposable = CompositeDisposable()
    fun getUsersFavorite(context: Context) = userRepository.getUserFavorite(context)
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}