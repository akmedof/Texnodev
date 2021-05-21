package com.texnodevcom.texnodev.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.texnodevcom.texnodev.model.Post

@Dao
interface PostDAO {

    @Insert
    suspend fun insertAll(vararg posts: Post) : List<Long>

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts() : List<Post>

    @Query("SELECT * FROM posts WHERE uuid = :postID")
    suspend fun getPostByID(postID: Int) : Post

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()

}