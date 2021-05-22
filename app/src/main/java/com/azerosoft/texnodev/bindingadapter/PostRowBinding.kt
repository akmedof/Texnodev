package com.azerosoft.texnodev.bindingadapter

import android.net.ParseException
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.azerosoft.texnodev.model.Post
import com.azerosoft.texnodev.util.downloadFromUrl
import com.azerosoft.texnodev.util.placeholderProgressBar
import com.azerosoft.texnodev.view.NewsFragmentDirections
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class PostRowBinding {

    companion object{

        @BindingAdapter("onPostClickListener")
        @JvmStatic
        fun onPostClickListener(postRowLayout: ConstraintLayout, post: Post){
            postRowLayout.setOnClickListener {
                try {
                    val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment()
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

        @BindingAdapter("setCategoriesOfName")
        @JvmStatic
        fun setCategoriesOfName(textView: TextView, post: Post){
            textView.text = post.categories[0].name.toString()
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