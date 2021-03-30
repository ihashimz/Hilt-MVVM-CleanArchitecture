package com.example.hilt_mvvm.core.network


/**
 * Created By @HashimHabtur on 2020-02-05
 */

/*
* Copyright 2018 Hashim Habtur
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


import okhttp3.Response
import retrofit2.HttpException

/**
 * Sealed class of HTTP result
 */
@Suppress("unused")
sealed class ApiResult<out T : Any> {
    /**
     * Successful result of request without errors
     */
    class Ok<out T : Any>(
        val value: T,
        override val response: Response
    ) : ApiResult<T>(), ResponseResult {
        override fun toString() = "Result.Ok{value=$value, response=$response}"
    }

    /**
     * HTTP error
     */
    class Error<out T : Any>(
        val value: T,
        override val exception: HttpException,
        override val response: Response
    ) : ApiResult<Nothing>(), ErrorResult, ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}"
    }

    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    class Exception(
        override val exception: Throwable
    ) : ApiResult<Nothing>(), ErrorResult {
        override fun toString() = "Result.Exception{$exception}"
    }

}

/**
 * Interface for [ApiResult] classes with [okhttp3.Response]: [ApiResult.Ok] and [ApiResult.Error]
 */
interface ResponseResult {
    val response: Response
}

/**
 * Interface for [ApiResult] classes that contains [Throwable]: [ApiResult.Error] and [ApiResult.Exception]
 */
interface ErrorResult {
    val exception: Throwable
}

/**
 * Returns [ApiResult.Ok.value] or `null`
 */
fun <T : Any> ApiResult<T>.getOrNull(): T? = (this as? ApiResult.Ok)?.value

/**
 * Returns [ApiResult.Ok.value] or [default]
 */
fun <T : Any> ApiResult<T>.getOrDefault(default: T): T = getOrNull() ?: default

/**
 * Returns [ApiResult.Ok.value] or throw [throwable] or [ErrorResult.exception]
 */
fun <T : Any> ApiResult<T>.getOrThrow(throwable: Throwable? = null): T {
    return when (this) {
        is ApiResult.Ok -> value
        is ApiResult.Error<*> -> throw throwable ?: exception
        is ApiResult.Exception -> throw throwable ?: exception
    }
}
