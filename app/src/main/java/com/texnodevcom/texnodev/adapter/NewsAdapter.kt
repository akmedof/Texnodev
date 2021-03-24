package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.databinding.ItemCountryBinding
import com.texnodevcom.texnodev.databinding.NewsItemRowBinding
import com.texnodevcom.texnodev.model.Post

class NewsAdapter(val posts: ArrayList<Post>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(var binding: NewsItemRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NewsItemRowBinding>(inflater, R.layout.news_item_row, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.post = posts[position]
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updateCountryList(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }


}