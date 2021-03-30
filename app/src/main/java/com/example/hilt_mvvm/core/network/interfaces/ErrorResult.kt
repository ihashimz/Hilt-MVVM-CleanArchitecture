package com.example.hilt_mvvm.core.network.interfaces


/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */

/**
 * Interface for [Result] classes that contains [Throwable]: [Result.Error] and [Result.Exception]
 */
interface ErrorResult {
    val exception: Throwable
}
