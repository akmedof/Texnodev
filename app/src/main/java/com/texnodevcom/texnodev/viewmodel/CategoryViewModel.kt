package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.model.Category

class CategoryViewModel(application: Application): BaseViewModel(application) {

    val categories = MutableLiveData<List<Category>>()

    fun getData(){

        val c2 = Category(1, "Bitcoin", R.drawable.bitcoin)
        val c3 = Category(2, "COVID-19", R.drawable.covid19)
        val c4 = Category(3, "Developer", R.drawable.dev)
        val c22 = Category(11, "Elm", R.drawable.elm)
        val c33 = Category(21, "Film", R.drawable.film)
        val c44 = Category(31, "game", R.drawable.game)
        val c21 = Category(12, "Internet", R.drawable.internet)
        val c31 = Category(22, "Mobil", R.drawable.mobil)
        val c41 = Category(32, "News", R.drawable.news)
        val c25 = Category(12, "Planet", R.drawable.planet)
        val c35 = Category(22, "Social Media", R.drawable.sosialmedia)
        val c45 = Category(32, "Video", R.drawable.video)
        val c46 = Category(32, "Yapay Zeka", R.drawable.yapay)

        val list = arrayListOf<Category>(c2,c21,c22,c3,c31,c33,c4,c41,c44,c25,c35,c45,c46)

        categories.value = list

    }

}