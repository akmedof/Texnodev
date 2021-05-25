package com.texnodevcom.texnodev.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.texnodevcom.texnodev.model.FavoriteEntity


//@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
//abstract class FavoriteDatabase : RoomDatabase() {
//
//    abstract fun favoriteDAO(): FavoriteDAO
//
//    companion object{
//
//        @Volatile private var instance : FavoriteDatabase? = null
//        private val lock = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(lock){
//            instance ?: makeDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun makeDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext, FavoriteDatabase::class.java, "favorite_db"
//        ).build()
//
//    }
//}