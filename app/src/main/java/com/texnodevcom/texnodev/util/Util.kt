@file:Suppress("UNREACHABLE_CODE")

package com.atilsamancioglu.kotlincountries.util

import android.content.Context
import android.net.ParseException
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.texnodevcom.texnodev.R
import java.text.SimpleDateFormat
import java.util.*


fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun getDateTimeDetails(date: String): String{

    val textView : String
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")


        val oldDate = dateFormat.parse(date)
        val currentDate = Date()
        val diff = currentDate.time - oldDate.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weekday = days / 7
        val month = days / 30
        val year = month / 12

        if (minutes in 1..59 && seconds in 0..3599){
            textView = "$minutes dəqiqə əvvəl"
        }else if(minutes in 60..1439){
            textView = "$hours saat əvvəl"
        }else if ( minutes in 1440..10079 ) {
            textView = "$days gün əvvəl"
        }else if(weekday in 1..4){
            textView = "$weekday həftə əvvəl"
        }else if (month in 1..11){
            textView = "$month ay əvvəl"
        }else{
            textView = "$year il əvvəl"
        }
        return textView


    return textView
}