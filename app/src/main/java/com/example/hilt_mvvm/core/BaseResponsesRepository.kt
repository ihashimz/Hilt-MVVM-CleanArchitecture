package com.example.hilt_mvvm.core.network

import androidx.lifecycle.MutableLiveData

import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */

object BaseResponsesRepository {

     inline fun <reified T> networkHandler(req: T): MutableLiveData<Resource<T>> {

        val mutableLiveData = MutableLiveData<Resource<T>>()

        try {
            val res = req
            mutableLiveData.value = Resource.success(
                res
            )
        } catch (e: Exception){
            when(e){
                is HttpException -> {
                    mutableLiveData.value = Resource.error(responseErrorsHandler(e.code()),null)
                }
                is SocketTimeoutException ->{
                    mutableLiveData.value = Resource.error("TimeoutException",null)
                }
                is UnknownHostException -> {
                    mutableLiveData.value = Resource.error("Network Error",null)
                }

                is JsonSyntaxException -> {
                    mutableLiveData.value = Resource.error("Casting Json Error",null)
                }
                else ->{
                    mutableLiveData.value = Resource.error(e.toString(),null)
                }
            }
        }

        return mutableLiveData

    }

    fun responseErrorsHandler(code: Int): String {
        return when(code){
            401 -> "Unauthorized"
            404 -> "Not Found"
            400 -> "Bad Request"
            500 -> "Server Error"
            502 -> "Gateway Error"
            504 -> "Server gateway timeout"
            else -> "Something went wrong"
        }
    }
}



