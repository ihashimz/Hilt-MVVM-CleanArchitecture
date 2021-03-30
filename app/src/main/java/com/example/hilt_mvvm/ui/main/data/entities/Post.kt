package com.example.hilt_mvvm.ui.main.data.entities


import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("userId")
    var userId: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("body")
    var body: String? = null,
)