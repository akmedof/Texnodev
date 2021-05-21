package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.databinding.PostItemRowBinding
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.util.NewsDiffUtil

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var posts = emptyList<Post>()

    class NewsViewHolder(private var binding: PostItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
//            println("POST: " + post.categories + "\n" + post.authorName + "\n" + post.authorImage)
            binding.post = post
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): NewsViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = PostItemRowBinding.inflate(inflater, parent, false)
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
        println("AUTHOR: " + post.authorName)
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