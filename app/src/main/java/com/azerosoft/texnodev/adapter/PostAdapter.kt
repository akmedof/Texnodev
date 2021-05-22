package com.azerosoft.texnodev.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azerosoft.texnodev.R
import com.azerosoft.texnodev.model.Post
import com.azerosoft.texnodev.util.downloadFromUrl
import com.azerosoft.texnodev.util.placeholderProgressBar
import kotlinx.android.synthetic.main.post_item_row.view.*

class PostAdapter(val posts: ArrayList<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_item_row, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.rowPostTitle.text = posts[position].title
        holder.itemView.rowPostDate.text = posts[position].date
        holder.itemView.rowPostCategory.text =  posts[position].categories[0].name.toString()
        holder.itemView.rowPostIMG.downloadFromUrl(posts[position].postImage, placeholderProgressBar(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDate(list: List<Post>){
        posts.clear()
        posts.addAll(list)
        notifyDataSetChanged()
    }
}