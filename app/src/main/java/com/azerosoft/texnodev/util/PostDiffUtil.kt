package com.azerosoft.texnodev.util

import androidx.recyclerview.widget.DiffUtil
import com.azerosoft.texnodev.model.Post

class PostDiffUtil(
    val oldPosts: List<Post>,
    val newPosts: List<Post>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPosts.size
    }

    override fun getNewListSize(): Int {
        return newPosts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition] === newPosts[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition] === newPosts[newItemPosition]
    }
}