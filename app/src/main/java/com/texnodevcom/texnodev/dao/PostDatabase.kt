package com.texnodevmedia.texnodev.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.texnodevcom.texnodev.dao.PostDAO
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.model.Post

@Database(entities = [Post::class, Favorite::class], version = 2)
abstract class PostDatabase : RoomDatabase() {

    abstract val postDAO : PostDAO

    //Singleton
    companion object{

        @Volatile private var instance : PostDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext, PostDatabase::class.java, "posts_database"
        ).build()

    }

//    companion object {
//        @Volatile
//        private var INSTANCE: PostDatabase? = null
//
//        fun getInstance(context: Context): PostDatabase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    PostDatabase::class.java,
//                    "posts_db"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }

}