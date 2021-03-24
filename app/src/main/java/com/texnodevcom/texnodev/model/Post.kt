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
        @ColumnInfo(name = "medium")
        @SerializedName("medium")
        val medium: String?){

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
