package com.bfaa.idchamp2020.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bfaa.idchamp2020.model.SearchResponses
import com.bfaa.idchamp2020.model.StateLoading
import com.bfaa.idchamp2020.model.UserGithub
import com.bfaa.idchamp2020.network.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(val api:ApiHelper) {
    private val compositeDisposable = CompositeDisposable()
    fun getUserSearch(keyword: String): LiveData<StateLoading<SearchResponses>> {
        val liveData = MutableLiveData<StateLoading<SearchResponses>>()
        compositeDisposable.add(
            api.getSearchUser(keyword)
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

    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}