package com.example.hilt_mvvm.core.network.interfaces

import okhttp3.Response


/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */

/**
 * Interface for [Result] classes with [okhttp3.Response]: [Result.Ok] and [Result.Error]
 */
interface ResponseResult {
    val response: Response
}
