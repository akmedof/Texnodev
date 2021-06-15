package com.texnodevcom.texnodev.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class TestPost(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("author_name")
    val authorName: String?,
    @SerializedName("author_image")
    val authorImage: String?,
    @SerializedName("medium")
    val postImage: String?)
