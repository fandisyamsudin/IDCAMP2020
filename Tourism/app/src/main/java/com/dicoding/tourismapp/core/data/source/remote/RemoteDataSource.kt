package com.dicoding.tourismapp.core.data.source.remote

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import com.dicoding.tourismapp.core.utils.JsonHelper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
       return flow {
           try {
               val response = apiService.getList()
               val dataArray = response.places
               if (dataArray.isNotEmpty()){
                   emit(ApiResponse.Success(dataArray))
               } else {
                   emit(ApiResponse.Empty)
               }

           } catch (e:Exception){
               emit(ApiResponse.Error(e.toString()))
               Log.e("RemoteDataSource", e.toString())
           }
       }.flowOn(Dispatchers.IO)
    }
}

