package com.jetpack.module012.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpack.module012.api.ApiInterface
import com.jetpack.module012.data.model.Movie
import com.jetpack.module012.data.model.TVShow
import com.jetpack.module012.utils.EspressoIdlingResource
import com.jetpack.module012.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.await
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) {

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<Resource<List<Movie>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiInterface.getMovies().await()
                resultMovieResponse.postValue(Resource.success(response.results!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    Resource.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    fun getTVShows(): LiveData<Resource<List<TVShow>>> {
        EspressoIdlingResource.increment()
        val resultTVResponse = MutableLiveData<Resource<List<TVShow>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiInterface.getTvShows().await()
                resultTVResponse.postValue(Resource.success(response.results!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTVResponse.postValue(
                    Resource.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTVResponse
    }
}