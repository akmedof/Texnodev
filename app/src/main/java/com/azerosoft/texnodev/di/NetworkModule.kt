package com.azerosoft.texnodev.di

import com.azerosoft.texnodev.services.PostAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRxJavaFactory() : RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }


    @Singleton
    @Provides
    fun provideConverterFactory() : GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://texnodev.com/wp-json/wl/v1/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }


    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): PostAPI{
        return retrofit.create(PostAPI::class.java)
    }

}