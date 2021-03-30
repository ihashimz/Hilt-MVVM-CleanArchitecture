package com.example.hilt_mvvm.core.network

import androidx.lifecycle.MutableLiveData
import com.example.hilt_mvvm.core.network.Resource
import com.example.hilt_mvvm.core.network.awaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import com.example.hilt_mvvm.core.network.Result
import com.example.hilt_mvvm.ui.main.data.service.PostsServices

/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */


suspend fun BaseRepository(req: retrofit2.Call<ResponseBody>): MutableLiveData<Resource<Any>> {

    val mutableLiveData = MutableLiveData<Resource<Any>>()
    try {
        withContext(Dispatchers.Main) {

            val result = req.awaitResult()
            // Check result type
            when (result) {

                //Successful HTTP result
                is Result.Ok -> {
                    mutableLiveData.value = Resource.success(
                        result.value.string()
                    )
                }
                // Any HTTP error
                is Result.Error -> {
                    mutableLiveData.value =
                        Resource.error(result.response.code.toString(), result.value.string())
                }
                // Exception while request invocation
                is Result.Exception -> Resource.error(
                    "Network Error!",
                    error(result.exception)
                )
            }
        }
    } catch (e: Exception) {
        mutableLiveData.value = Resource.error("Something went wrong, please try again", "")

    }
    return mutableLiveData
}

