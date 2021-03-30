package com.example.hilt_mvvm.core.network

import androidx.lifecycle.MutableLiveData
import com.example.hilt_mvvm.core.network.Resource
import com.example.hilt_mvvm.core.network.awaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import com.example.hilt_mvvm.core.network.Result
import com.example.hilt_mvvm.ui.main.data.service.PostsServices
import com.google.gson.Gson
import retrofit2.Call

/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */


suspend inline fun <reified T> BaseRepository(req: Call<ResponseBody>): MutableLiveData<Resource<T>> {

    val mutableLiveData = MutableLiveData<Resource<T>>()
    try {
        withContext(Dispatchers.Main) {

            val result = req.awaitResult()
            // Check result type
            when (result) {

                //Successful HTTP result
                is Result.Ok -> {
                    mutableLiveData.value = Resource.success(
                        Gson().fromJson(result.value.string(),T::class.java)
                    )
                }
                // Any HTTP error
                is Result.Error -> {
                    mutableLiveData.value =
                        Resource.error(result.response.code.toString(), null)
                }
                // Exception while request invocation
                is Result.Exception -> Resource.error(
                    "Network Error!",
                    error(result.exception)
                )
            }
        }
    } catch (e: Exception) {
        mutableLiveData.value = Resource.error("Something went wrong, please try again", null)

    }
    return mutableLiveData
}

