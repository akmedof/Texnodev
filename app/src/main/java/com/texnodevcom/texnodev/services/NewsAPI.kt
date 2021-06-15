package com.texnodevcom.texnodev.services

import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.model.TestPost
import io.reactivex.Single
import retrofit2.http.GET

interface NewsAPI {

    @GET("posts")
    fun getPosts(): Single<List<Post>>


    @GET("posts")
    fun getTestPosts(): Single<List<TestPost>>

}