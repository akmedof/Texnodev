package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.databinding.CategoryRowItemBinding
import com.texnodevcom.texnodev.model.Categories
import com.texnodevcom.texnodev.util.CategoryDiffUtil

class CategoryAdapter():
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    private var categories = emptyList<Categories>()

    class MyViewHolder(private var binding: CategoryRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Categories){
            binding.category = category
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = CategoryRowItemBinding.inflate(inflater, parent, false)
                return MyViewHolder(binding)
            }
        }
//        val imageIcon = view.findViewById<ImageView>(R.id.catRowIcon)
//        val imageBG = view.findViewById<ImageView>(R.id.catRowImgBG)
//        val name = view.findViewById<TextView>(R.id.catRowName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
//        val infalte = LayoutInflater.from(parent.context)
//        val view = infalte.inflate(R.layout.category_row_item, parent, false)
//        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
//        holder.imageIcon.setImageResource(category.icon)
//        holder.imageBG.setImageResource(category.bg)
//        holder.name.text = category.name
//
//        holder.view.setOnClickListener {
//            Toast.makeText(holder.view.context, category.name, Toast.LENGTH_LONG).show()
//        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateCategoryList(newList: List<Categories>) {
        val categoryDiffUtil = CategoryDiffUtil(newList, categories)
        val diffUtilCategory = DiffUtil.calculateDiff(categoryDiffUtil)
        categories = newList
        diffUtilCategory.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }



}