package com.azerosoft.texnodev.services

import com.azerosoft.texnodev.model.Post
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface PostAPI {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

}