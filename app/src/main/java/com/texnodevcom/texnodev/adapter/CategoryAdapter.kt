package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.model.CategoryNews

class CategoryAdapter():
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    private var categories = emptyList<CategoryNews>()

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val imageIcon = view.findViewById<ImageView>(R.id.catRowIcon)
        val imageBG = view.findViewById<ImageView>(R.id.catRowImgBG)
        val name = view.findViewById<TextView>(R.id.catRowName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val infalte = LayoutInflater.from(parent.context)
        val view = infalte.inflate(R.layout.category_row_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val category = categories[position]
        holder.imageIcon.setImageResource(category.icon)
        holder.imageBG.setImageResource(category.bg)
        holder.name.text = category.name

        holder.view.setOnClickListener {
            Toast.makeText(holder.view.context, category.name, Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun updateCategoryList(newList: List<CategoryNews>) {
        categories = newList
        notifyDataSetChanged()
    }



}