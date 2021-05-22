package com.azerosoft.texnodev.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.azerosoft.texnodev.model.Post
import com.azerosoft.texnodev.util.Constants.Companion.POST_TABLE


@Entity(tableName = POST_TABLE)
class PostEntity(
    var post: Post
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}