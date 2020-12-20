package com.bfaa.idchamp2020.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bfaa.idchamp2020.model.StateLoading
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.network.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileRepository@Inject constructor(
    private val api: ApiHelper,
    private val userRepository: UserRepository) {
    private val compositeDisposable = CompositeDisposable()
    fun getUserDetail(username: String): LiveData<StateLoading<UserGithub>> {
        val liveData = MutableLiveData<StateLoading<UserGithub>>()
        compositeDisposable.add(
            api.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(StateLoading.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(StateLoading.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(StateLoading.error("Error : ${it.message}", null))
                    }
                )
        )
        return liveData
    }

    fun getUserFollower(username: String): LiveData<StateLoading<List<UserGithub>>> {
        val liveData = MutableLiveData<StateLoading<List<UserGithub>>>()
        compositeDisposable.add(
            api.getUserFollowers(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(StateLoading.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(StateLoading.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(StateLoading.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    fun getUserFollowing(username: String): LiveData<StateLoading<List<UserGithub>>> {
        val liveData = MutableLiveData<StateLoading<List<UserGithub>>>()

        compositeDisposable.add(
            api.getUserFollowing(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(StateLoading.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(StateLoading.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(StateLoading.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    fun checkFavoriteUser(userId: Int, context: Context) =
        userRepository.checkFavorite(userId, context)

    fun addFavoriteUser(user: UserGithub, context: Context) =
        userRepository.addFavorite(user, context)

    fun deleteFavoriteUser(user: UserGithub, context: Context) =
        userRepository.deleteFavorite(user, context)

    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}