package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.databinding.PostItemRowBinding
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.util.CategoryDetailsDiffUtil

class CategoryDetailsAdapter : RecyclerView.Adapter<CategoryDetailsAdapter.MyDetViewHolder>() {

    private var posts = emptyList<Post>()

    class MyDetViewHolder(private var binding : PostItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post){
            binding.post = post
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyDetViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = PostItemRowBinding.inflate(inflater, parent, false)
                return MyDetViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDetViewHolder {
        return MyDetViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyDetViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setData(newData: List<Post>){
        val postDiffUtil = CategoryDetailsDiffUtil(newData, posts)
        val diffUtilPost = DiffUtil.calculateDiff(postDiffUtil)
        posts = newData
        diffUtilPost.dispatchUpdatesTo(this)
    }


}