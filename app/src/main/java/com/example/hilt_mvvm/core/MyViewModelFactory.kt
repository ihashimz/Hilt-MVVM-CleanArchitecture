package com.example.hilt_mvvm.core.network

import android.app.Application
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider





/**
 * Created By @HashimHabtur on 14/09/2020 AD
 */

class MyViewModelFactory(application: Application) :
    ViewModelProvider.Factory {
    private val mApplication: Application


    init {
        mApplication = application
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModelFactory(mApplication) as T
    }
}