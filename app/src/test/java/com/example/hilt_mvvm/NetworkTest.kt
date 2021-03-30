package com.example.hilt_mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.hilt_mvvm.core.network.Resource
import com.example.hilt_mvvm.ui.main.data.entities.Post
import com.example.hilt_mvvm.ui.main.data.repository.PostRepository
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.*
import kotlin.collections.ArrayList

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
    fun testMutableGetPosts() = runBlocking {
        getPosts()
    }

    @Test
    fun testMutableGetPost() = runBlocking {
            getMutablePost(2)
    }

    @Test
    fun testGetPosts()= runBlocking {
        getPosts2()
    }

    @Test
    fun testGetPost() = runBlocking {
        getPost()
    }


    suspend fun getPosts()  {
        val res = PostRepository().getPosts()!!
        res.observeForever { response ->
            when (response.status) {

                Resource.Status.SUCCESS -> {
                    val list = response.data
                    print(list!!)
                }

                Resource.Status.LOADING -> {
                    println("Loading")
                }

                Resource.Status.ERROR -> {
                    println(res.value!!.message)
                }
            }
        }
    }

    private suspend fun getMutablePost(id:Int){
        val res = PostRepository().getMutablePost(1)!!
        print(res.value!!.data)
    }

    suspend fun getPost(){
        val res = PostRepository().getPost(1)
        print(res)
    }

    suspend fun getPosts2(){
        val res = PostRepository().getPosts2()
        print(res)
    }
}
