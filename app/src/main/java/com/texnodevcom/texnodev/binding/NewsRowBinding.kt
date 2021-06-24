package com.texnodevcom.texnodev.binding

import android.app.Application
import android.net.ParseException
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.view.*
import com.texnodevcom.texnodev.viewmodel.BaseViewModel
import com.texnodevcom.texnodev.viewmodel.FavoriteViewModel
import com.texnodevmedia.texnodev.dao.PostDatabase
import java.text.SimpleDateFormat
import java.util.*

class NewsRowBinding {


    companion object {

        val userFoto = "https://secure.gravatar.com/avatar/8d284c2b438d80d134bbd866b38dca86?s=96&#038;d=blank&#038;r=g"
        private lateinit var viewModelFav : FavoriteViewModel

        @BindingAdapter("android:downloadUserUrl")
        @JvmStatic
        fun downloadUserImage(view: ImageView, url:String?) {
            if (url.equals(userFoto)){
                view.setImageResource(R.drawable.user)
            }else{
                view.downloadFromUrl(url, placeholderProgressBar(view.context))
            }

        }

        @BindingAdapter("onFavoriteClickListener")
        @JvmStatic
        fun onFavoriteClickListener(rowPostLayout: ConstraintLayout, id: Int){
            rowPostLayout.setOnClickListener {
                try {
                    val action = FavoriteFragmentDirections.actionFavoriteFragmentToPostDetailsFragment(id, true)
                    rowPostLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onFavoriteClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("onPostClickListener")
        @JvmStatic
        fun onPostClickListener(rowPostLayout: ConstraintLayout, id: Int){
            rowPostLayout.setOnClickListener {
                try {
                    val action = NewsFragmentDirections.actionNewsFragmentToPostDetailsFragment(id)
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