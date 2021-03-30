package com.example.hilt_mvvm.ui.main.data.service

import com.example.hilt_mvvm.ui.main.data.entities.Post
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsServices {

    @GET("/posts/{id}")
    suspend fun getPost(@Path(value = "id") postId: Int): Post

    @GET("/posts")
    suspend fun getPosts(): ArrayList<Post>
}
