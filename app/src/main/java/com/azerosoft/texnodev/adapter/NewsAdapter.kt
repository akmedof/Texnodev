package com.azerosoft.texnodev.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azerosoft.texnodev.databinding.PostItemRowBinding
import com.azerosoft.texnodev.model.Post
import com.azerosoft.texnodev.util.PostDiffUtil

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var posts = emptyList<Post>()

    class NewsViewHolder(private var binding: PostItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.post = post
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): NewsViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostItemRowBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setData(newPosts: List<Post>){
        val postsDiffUtil = PostDiffUtil(posts, newPosts)
        val diffUtilPost = DiffUtil.calculateDiff(postsDiffUtil)
        posts = newPosts
        diffUtilPost.dispatchUpdatesTo(this)
    }
}