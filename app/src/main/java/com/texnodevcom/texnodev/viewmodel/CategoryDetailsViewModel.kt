package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.model.Post
import com.texnodevmedia.texnodev.dao.PostDatabase
import kotlinx.coroutines.launch

class CategoryDetailsViewModel(application: Application): BaseViewModel(application) {

    val posts = MutableLiveData<List<Post>>()
    val dao = PostDatabase(getApplication()).postDAO

    fun getData(name: String){
        launch {
            val daoPosts = dao.getPostByCategory(name)
            posts.value = daoPosts
        }
    }

}