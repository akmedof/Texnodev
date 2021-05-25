package com.texnodevcom.texnodev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "content")
    val content: String?,
//    @ColumnInfo(name = "categories")
//    @SerializedName("categories")
//    val categories: String,
    @ColumnInfo(name = "author_name")
    val authorName: String?,
    @ColumnInfo(name = "author_image")
    val authorImage: String?,
    @ColumnInfo(name = "post_image")
    val postImage: String?,
    @PrimaryKey(autoGenerate = true)
    var dbID: Int? = null)