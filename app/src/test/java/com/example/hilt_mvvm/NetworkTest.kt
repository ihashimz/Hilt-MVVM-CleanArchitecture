package com.example.hilt_mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.hilt_mvvm.core.network.Resource
import com.example.hilt_mvvm.ui.main.data.entities.Post
import com.example.hilt_mvvm.ui.main.data.repository.PostRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }



    @Test
    fun testGetPost() = runBlocking {

        val postRequest = PostRepository().getPostMutableNewApi(1).value

        when (postRequest!!.status) {

            Resource.Status.SUCCESS -> {
                print(postRequest.data)
            }

            Resource.Status.ERROR -> {
                print("${postRequest.status} ${postRequest.message}")
            }

        }

    }

}
