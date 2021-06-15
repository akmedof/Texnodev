package com.texnodevcom.texnodev.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity
data class Category(
//    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String){

//    @PrimaryKey(autoGenerate = true)
//    var uuid: Int = 0

}