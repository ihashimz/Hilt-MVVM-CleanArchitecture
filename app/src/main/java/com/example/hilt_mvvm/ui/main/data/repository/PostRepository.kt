package com.example.hilt_mvvm.ui.main.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.hilt_mvvm.core.network.BaseRepository
import com.example.hilt_mvvm.core.network.Resource
import com.example.hilt_mvvm.core.network.ServiceGenerator
import com.example.hilt_mvvm.ui.main.data.entities.Post
import com.example.hilt_mvvm.ui.main.data.service.PostsServices

val postsServices = ServiceGenerator().createService(PostsServices::class.java)
class PostRepository {

    suspend fun getPosts() : MutableLiveData<Resource<Any>> {
        val mutableLiveData = MutableLiveData<Resource<ArrayList<Post>?>>()
        mutableLiveData.value = Resource.loading(null)
        val req = postsServices!!.getPosts()
        return BaseRepository(req)
    }


//    suspend fun getPost(id: Int) : MutableLiveData<Resource<Post>>  {
//        val mutableLiveData = MutableLiveData<Resource<Post>>()
//        mutableLiveData.value = postsServices.getPost(id)
//        postsServices.getPost(id)
//    }
suspend fun getPost(id:Int) = postsServices.getPost(id)
    suspend fun getPosts2() = postsServices.getPosts2()
}