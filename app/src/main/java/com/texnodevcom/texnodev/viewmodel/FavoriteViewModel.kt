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


    fun getAllFavorite(){
        launch {
            val list: List<Favorite> = dao.getAllFavorite()
            favorites.value = list
        }
    }

}