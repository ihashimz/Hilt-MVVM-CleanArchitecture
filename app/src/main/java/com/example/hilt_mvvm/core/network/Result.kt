package com.example.hilt_mvvm.core.network

import  okhttp3.Response
import retrofit2.HttpException
import okhttp3.ResponseBody


/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */


/**
 * Sealed class of HTTP result
 */
@Suppress("unused")
sealed class Result<out T : Any> {
    /**
     * Successful result of request without errors
     */
    class Ok<out T : Any>(
        val value: T,
        override val response: Response
    ) : Result<T>(), ResponseResult {
        override fun toString() = "Result.Ok{value=$value, response=$response}"
    }

    /**
     * HTTP error
     */
    class Error(
        override val exception: HttpException,
        override val response: Response,
        val value: ResponseBody
    ) : Result<Nothing>(), ErrorResult, ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}, response=$value}"
    }

    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    class Exception(
        override val exception: Throwable
    ) : Result<Nothing>(), ErrorResult {
        override fun toString() = "Result.Exception{$exception}"
    }

}
/**
 * Returns [Result.Ok.value] or `null`
 */
fun <T : Any> Result<T>.getOrNull(): T? = (this as? Result.Ok)?.value

/**
 * Returns [Result.Ok.value] or [default]
 */
fun <T : Any> Result<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Returns [Result.Ok.value] or throw [throwable] or [ErrorResult.exception]
 */
fun <T : Any> Result<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is Result.Ok -> value
        is Result.Error -> throw throwable ?: exception
        is Result.Exception -> throw throwable ?: exception
    }
}
