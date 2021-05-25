package com.texnodevcom.texnodev.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.texnodevcom.texnodev.model.FavoriteEntity
import com.texnodevcom.texnodev.model.Post

//@Dao
//interface FavoriteDAO {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertPost(favoriteEntity: FavoriteEntity)
//
//    @Delete
//    suspend fun deletePost(favoriteEntity: FavoriteEntity)
//
//    @Query("SELECT * FROM favorite_table")
//    fun observePosts(): LiveData<List<FavoriteEntity>>
//}