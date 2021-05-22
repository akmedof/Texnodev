package com.texnodevcom.texnodev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "posts")
data class Post(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo(name = "date")
    @SerializedName("date")
    val date: String?,
    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String?,
//    @ColumnInfo(name = "categories")
//    @SerializedName("categories")
//    val categories: String,
    @ColumnInfo(name = "author_name")
    @SerializedName("author_name")
    val authorName: String?,
    @ColumnInfo(name = "author_image")
    @SerializedName("author_image")
    val authorImage: String?,
    @ColumnInfo(name = "post_image")
    @SerializedName("medium")
    val postImage: String?){

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}
