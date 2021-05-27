package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.databinding.PostItemRowBinding
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.util.NewsDiffUtil
import com.texnodevcom.texnodev.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.post_item_row.view.*
import kotlin.coroutines.coroutineContext

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var posts = emptyList<Post>()
    private lateinit var viewModelFav: FavoriteViewModel

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
        val post = posts[position]
        holder.bind(post)
//        val checkBox = holder.itemView.turnedFavID
//
//        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
//
//            if (checkBox.isChecked){
//                checkBox.setButtonDrawable(R.drawable.turned_in_texnodev)
////                post.id?.let { viewModelFav.setFavoriteByID(it) }
//                Toast.makeText(holder.itemView.context, "Checked " + post.id, Toast.LENGTH_LONG).show()
//            }else{
//                checkBox.setButtonDrawable(R.drawable.turned_in_not_favorite)
//                Toast.makeText(holder.itemView.context, "Not Checked ", Toast.LENGTH_LONG).show()
//            }
//
//        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun updatePostList(newPosts: List<Post>) {
        val newsDiffUtil = NewsDiffUtil(posts, newPosts)
        val diffUtilNews = DiffUtil.calculateDiff(newsDiffUtil)
        posts = newPosts
        diffUtilNews.dispatchUpdatesTo(this)
    }


}