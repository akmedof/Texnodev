package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.model.CategoryNews

class CategoryViewModel(application: Application): BaseViewModel(application) {

    val categories = MutableLiveData<List<CategoryNews>>()

    fun getData(){

//        val c2 = CategoryNews(1, "Bitcoin", R.drawable.bitcoinicon)
//        val c3 = CategoryNews(2, "COVID-19", R.drawable.covid19)
//        val c4 = CategoryNews(3, "Developer", R.drawable.dev)
//        val c22 = CategoryNews(11, "Elm", R.drawable.elm)
//        val c33 = CategoryNews(21, "Film", R.drawable.movie)
        val c44 = CategoryNews(31, "Oyun", R.drawable.gameicon, R.drawable.gamebg)
        val c21 = CategoryNews(12, "Internet", R.drawable.internet, R.drawable.netowrkbg)
        val c31 = CategoryNews(22, "Mobil", R.drawable.phoneicon, R.drawable.phonebg)
        val c41 = CategoryNews(32, "XƏbər", R.drawable.xebericon, R.drawable.newsbg)
        val c25 = CategoryNews(12, "Planet", R.drawable.planeticon, R.drawable.planeticon)
        val c35 = CategoryNews(22, "Social Media", R.drawable.socialicon, R.drawable.socalmediabg)
        val c45 = CategoryNews(32, "Texnologiya", R.drawable.techicon, R.drawable.techbg)
        val c46 = CategoryNews(32, "Yapay Zeka", R.drawable.suniintelicon, R.drawable.suniintelectbg)

        val list = arrayListOf<CategoryNews>(c21,c31,c41,c44,c25,c35,c45,c46)

        categories.value = list

    }

}