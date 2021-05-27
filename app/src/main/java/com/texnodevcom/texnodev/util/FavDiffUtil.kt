package com.texnodevcom.texnodev.util

import androidx.recyclerview.widget.DiffUtil
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.model.Post

class FavDiffUtil(
    private val oldList : List<Favorite>,
    private val newList : List<Favorite>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }
}