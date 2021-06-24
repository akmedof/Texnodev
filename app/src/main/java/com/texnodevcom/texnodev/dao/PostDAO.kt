package com.texnodevcom.texnodev.dao

import androidx.room.*
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.model.Post

@Dao
interface PostDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg posts: Post) : List<Long>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTestAll(vararg posts: TestPost) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(vararg favorite: Favorite)

    @Transaction
    @Query("SELECT * FROM posts")
    suspend fun getAllPosts() : List<Post>

    @Transaction
    @Query("SELECT * FROM posts WHERE id = :postID")
    suspend fun getPostByID(postID: Int) : Post

    @Transaction
    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()

    @Transaction
    @Query("SELECT * FROM favorite")
    suspend fun getAllFavorite(): List<Favorite>

    @Transaction
    @Query("SELECT * FROM posts WHERE categories = :categoryName")
    suspend fun getPostByCategory(categoryName: String): List<Post>

    @Transaction
    @Query("DELETE FROM favorite")
    suspend fun deleteAllFavorite()

    @Query("SELECT * FROM favorite WHERE uuid = :id")
    suspend fun getFavoriteByID(id: Int): Favorite

    @Delete
    suspend fun deleteFavorite(vararg favorite: Favorite)

    @Query("DELETE FROM favorite WHERE id = :favID")
    suspend fun deleteFavoriteByID(favID: Int)


    @Query("DELETE FROM favorite WHERE id = :favID")
    fun deleteFavoriteByID2(favID: Int)
}