package com.example.hilt_mvvm.ui.main.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.hilt_mvvm.core.network.BaseResponsesRepository
import com.example.hilt_mvvm.core.network.Resource
import com.example.hilt_mvvm.core.network.ServiceGenerator
import com.example.hilt_mvvm.ui.main.data.entities.Post
import com.example.hilt_mvvm.ui.main.data.service.PostsServices

val postsServices = ServiceGenerator().createService(PostsServices::class.java)

class PostRepository {

    suspend fun getPost(id: Int) : MutableLiveData<Resource<Post>>{
        return BaseResponsesRepository.networkHandler(postsServices.getPost(id))
    }

    suspend fun getPosts(): MutableLiveData<Resource<ArrayList<Post>>> {
        return BaseResponsesRepository.networkHandler(postsServices.getPosts())
    }

}