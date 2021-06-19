package com.texnodevcom.texnodev.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

class CategoryRowBinding {

    companion object{

        @BindingAdapter("loadCategoryImage")
        @JvmStatic
        fun loadCategoryImage(imageView: ImageView, img: Int){
            imageView.setImageResource(img)
        }

    }

}