package com.texnodevcom.texnodev.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.texnodevcom.texnodev.databinding.FavoriteItemRowBinding
import com.texnodevcom.texnodev.databinding.FragmentFavoriteBinding
import com.texnodevcom.texnodev.databinding.PostItemRowBinding
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.util.FavDiffUtil
import com.texnodevcom.texnodev.util.NewsDiffUtil

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    private var favorites = emptyList<Favorite>()

    class FavViewHolder(private var binding: FavoriteItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite){
            binding.favorite = favorite
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): FavViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = FavoriteItemRowBinding.inflate(inflater, parent, false)
                return FavoriteAdapter.FavViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.bind(favorite)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    fun updateFavoriteList(newData: List<Favorite>) {
        val favDiffUtil = FavDiffUtil(favorites, newData)
        val diffUtilFav = DiffUtil.calculateDiff(favDiffUtil)
        favorites = newData
        diffUtilFav.dispatchUpdatesTo(this)
    }


}