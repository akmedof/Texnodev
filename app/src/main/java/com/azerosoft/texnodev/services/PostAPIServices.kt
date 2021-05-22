package com.azerosoft.texnodev.services

import com.azerosoft.texnodev.model.Post
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PostAPIServices @Inject constructor(
    private val postAPI: PostAPI
) {

//    private val BASE_URL = "https://texnodev.com/wp-json/wl/v1/"
//    private val api = Retrofit.Builder()
//        .baseUrl("https://texnodev.com/wp-json/wl/v1/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(PostAPI::class.java)

    fun getData() : Single<List<Post>> {
        return postAPI.getPosts()
    }
}