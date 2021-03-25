package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.databinding.NewsItemRowBinding
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.util.NewsDiffUtil
import com.texnodevcom.texnodev.viewmodel.NewsViewModel

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var posts = emptyList<Post>()

    class NewsViewHolder(private var binding: NewsItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.post = post
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): NewsViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = NewsItemRowBinding.inflate(inflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = DataBindingUtil.inflate<NewsItemRowBinding>(inflater, R.layout.news_item_row, parent, false)
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        holder.binding.post = posts[position]
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updateCountryList(newPosts: List<Post>) {
        val newsDiffUtil = NewsDiffUtil(posts, newPosts)
        val diffUtilNews = DiffUtil.calculateDiff(newsDiffUtil)
        posts = newPosts
        diffUtilNews.dispatchUpdatesTo(this)
    }


}