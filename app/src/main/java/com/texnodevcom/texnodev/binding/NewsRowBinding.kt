package com.texnodevcom.texnodev.binding

import android.net.ParseException
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.google.android.material.card.MaterialCardView
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.view.NewsFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class NewsRowBinding {

    companion object {


        @BindingAdapter("onPostClickListener")
        @JvmStatic
        fun onPostClickListener(rowPostLayout: ConstraintLayout, postID: Int){
            rowPostLayout.setOnClickListener {
                try {
                    val action = NewsFragmentDirections.actionNewsFragmentToDetailsActivity(postID)
                    rowPostLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onPostClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("android:downloadUrl")
        @JvmStatic
        fun downloadImage(view: ImageView, url:String?) {
            view.downloadFromUrl(url, placeholderProgressBar(view.context))
        }

        @BindingAdapter("getDateTimeSubtract")
        @JvmStatic
        fun getDateTimeSubtract(textView: TextView, date: String){

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

            try {
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
                    textView.text = "$minutes dəqiqə əvvəl"
                }else if(minutes in 60..1439){
                    textView.text = "$hours saat əvvəl"
                }else if ( minutes in 1440..10079 ) {
                    textView.text = "$days gün əvvəl"
                }else if(weekday in 1..4){
                    textView.text = "$weekday həftə əvvəl"
                }else if (month in 1..11){
                    textView.text = "$month ay əvvəl"
                }else{
                    textView.text = "$year il əvvəl"
                }

            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

    }


}