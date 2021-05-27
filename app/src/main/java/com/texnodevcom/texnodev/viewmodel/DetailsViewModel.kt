package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.model.Post
import com.texnodevmedia.texnodev.dao.PostDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application) {

    val postLiveData = MutableLiveData<Post>()
    val dao = PostDatabase(getApplication()).postDAO

    fun getPostByID(uuid: Int){
        launch {
            val post = dao.getPostByID(uuid)
            postLiveData.value = post
        }
    }

    fun insertFAV(p: Post){
        launch {
            val fav: Favorite = Favorite(p.id, p.title, p.date, p.content, p.authorName, p.authorImage, p.postImage)
            dao.insertFavorite(fav)
        }
    }

}