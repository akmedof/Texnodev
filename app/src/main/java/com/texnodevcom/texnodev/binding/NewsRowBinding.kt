package com.texnodevcom.texnodev.binding

import android.util.Log
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.texnodevcom.texnodev.view.NewsFragmentDirections

class NewsRowBinding {

    companion object {

        @BindingAdapter("onNewsClickListener")
        @JvmStatic
        fun onNewsClickListener(newsRowLayout: LinearLayout, id: Int) {
            Log.d("onNewsClickListener", "CALLED")
            newsRowLayout.setOnClickListener {
                try {
                    val action = NewsFragmentDirections.actionNewsFragmentToDetailsActivity(id)
                    newsRowLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onNewsClickListener", e.toString())
                }
            }

        }

    }


}