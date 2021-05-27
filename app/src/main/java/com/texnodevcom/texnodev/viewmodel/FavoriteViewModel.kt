package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.model.Post
import com.texnodevmedia.texnodev.dao.PostDatabase
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : BaseViewModel(application) {

    val favorites = MutableLiveData<List<Favorite>>()
    val dao = PostDatabase(getApplication()).postDAO

    fun insertFAV(p: Post){
        launch {
            val fav: Favorite = Favorite(p.id, p.title, p.date, p.content, p.authorName, p.authorImage, p.postImage)
            dao.insertFavorite(fav)
        }
    }

    fun setFavoriteByID(id: Int){
        launch {
            val p = dao.getPostByID(id)
            val fav = Favorite(p.id, p.title, p.date, p.content, p.authorName, p.authorImage, p.postImage)
            dao.insertFavorite(fav)
        }
    }

    fun deleteFavByID(favorite: Favorite){
        launch {
            dao.deleteFavorite(favorite)
        }
    }

    fun getFavorite(uuid: Int){
        launch {
            dao.getFavoriteByID(uuid)
        }
    }

    fun getAllFavorite(){
        launch {
            val list: List<Favorite> = dao.getAllFavorite()
            favorites.value = list
        }
    }

}