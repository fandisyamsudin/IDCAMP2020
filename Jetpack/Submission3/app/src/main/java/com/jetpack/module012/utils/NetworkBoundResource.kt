package com.jetpack.module012.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jetpack.module012.vo.Resource
import com.jetpack.module012.vo.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<Resource<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                Status.SUCCESS ->
                    CoroutineScope(IO).launch {
                        response.data?.let { saveCallResult(it) }
                        Log.d("BOUND 1 : ", response.status.name)

                        withContext(Main) {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.success(newData)
                            }
                        }

                    }

                Status.ERROR -> {
                    onFetchFailed()
                    Log.d("BOUND 2 : ", response.status.name)
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
                Status.LOADING -> {
                    Log.d("BOUND 3 :", response.status.name)
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}