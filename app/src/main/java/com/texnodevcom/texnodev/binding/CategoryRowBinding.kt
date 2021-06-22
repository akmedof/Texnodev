package com.texnodevcom.texnodev.binding

import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.texnodevcom.texnodev.view.CategoryDetailsFragmentDirections
import com.texnodevcom.texnodev.view.CategoryFragmentDirections
import com.texnodevcom.texnodev.view.NewsFragmentDirections

class CategoryRowBinding {

    companion object{

        @BindingAdapter("loadCategoryImage")
        @JvmStatic
        fun loadCategoryImage(imageView: ImageView, img: Int){
            imageView.setImageResource(img)
        }

        @BindingAdapter("onCategoryClickListener")
        @JvmStatic
        fun onCategoryClickListener(categoryLayoutRow: ConstraintLayout, id: Int){
            categoryLayoutRow.setOnClickListener {
                try {
                    val action = CategoryFragmentDirections
                        .actionCategoryFragmentToCategoryDetailsFragment(id)
                    categoryLayoutRow.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onCategoryClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("onCategoryPostClickListener")
        @JvmStatic
        fun onCategoryPostClickListener(rowPostLayout: ConstraintLayout, id: Int){
            rowPostLayout.setOnClickListener {
                try {
                    val action = CategoryDetailsFragmentDirections
                        .actionCategoryDetailsFragmentToPostDetailsFragment(id)
                    rowPostLayout.findNavController().navigate(action)
                }catch (e: Exception){
                    Log.d("onCategoryPostClickListener", e.toString())
                }
            }
        }

    }


}