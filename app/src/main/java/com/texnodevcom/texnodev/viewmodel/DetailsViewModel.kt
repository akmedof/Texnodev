package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.model.Post
import com.texnodevmedia.texnodev.dao.PostDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application) {

    val postLiveData = MutableLiveData<Post>()

    fun getPostByID(uuid: Int){
        launch {
            val dao = PostDatabase(getApplication()).postDAO()
            val post = dao.getPostByID(uuid)
            postLiveData.value = post
        }
    }

}