package com.example.hilt_mvvm.core.network


/**
 * Created By @HashimHabtur on 24/06/2020 AD
 */

class Resource<T> private constructor(
    val status: Status, val data: T?,
    val message: String?
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg.toString()
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
