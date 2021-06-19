package com.texnodevcom.texnodev.services

import com.texnodevcom.texnodev.model.Post
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiServices {

//    private val BASE_URL = "https://texnodev.com/wp-json/wl/v1/"
    private val api = Retrofit.Builder()
        .baseUrl("https://texnodev.com/wp-json/wl/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)


    fun getData() : Single<List<Post>> {
        return api.getPosts()
    }

}