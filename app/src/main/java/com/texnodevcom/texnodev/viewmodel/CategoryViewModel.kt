package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.model.Categories

class CategoryViewModel(application: Application): BaseViewModel(application) {

    val categories = MutableLiveData<List<Categories>>()

    fun getData(){

        val c1 = Categories(1, "Kripto Pul", R.drawable.bitcoin_icon, R.drawable.bitcoinbg)
        val c2 = Categories(2, "COVID-19", R.drawable.covid19_icon, R.drawable.covidbg)
        val c3 = Categories(3, "Proqram Təminatı", R.drawable.developer_icon, R.drawable.developerbg)
        val c4 = Categories(4, "Elm", R.drawable.science_icon, R.drawable.sciencebg)
        val c5 = Categories(5, "Kino və Serial", R.drawable.movie_icon, R.drawable.moviebg)
        val c6 = Categories(6, "Süni İntellekt", R.drawable.artificial_intelligence_icon, R.drawable.intelektbg)
        val c7 = Categories(7, "Oyun", R.drawable.game_icon, R.drawable.gamebg)
        val c8 = Categories(8, "İnternet", R.drawable.internet_icon, R.drawable.internetbg)
        val c9 = Categories(9, "Sosial Media", R.drawable.social_media_icon, R.drawable.socalmediabg)
        val c10 = Categories(10, "Texnologiya", R.drawable.technology_icon, R.drawable.technologybg)
        val c11 = Categories(11, "Mobil", R.drawable.mobile_icon, R.drawable.phonebg)
        val c12 = Categories(12, "Avtomobil", R.drawable.cars_icon, R.drawable.carsbg)
        val c13 = Categories(13, "Mobil Tətbiqetmə", R.drawable.mobil_app_icon, R.drawable.androidbg)
        val c14 = Categories(14, "İş Sektoru", R.drawable.business_sector_icon, R.drawable.isbg)
        val c15 = Categories(15, "Xəbər", R.drawable.news_icon, R.drawable.newsbg)
        val c16 = Categories(16, "Kosmos", R.drawable.planet_icon, R.drawable.planetbg)
        val c17 = Categories(17, "Həyat", R.drawable.heyat_icon, R.drawable.heyatbg)
        val c18 = Categories(18, "Tarix", R.drawable.tarix_icon, R.drawable.historybg)
        val c19 = Categories(19, "Avadanlıq", R.drawable.avaddanlq_icon, R.drawable.avadanligbg)

        val list = arrayListOf<Categories>(c1,c2,c7,c4,c16,c6,c3,c8,c9,c10,c11,c12,c13,c14,c15,c5,c17,c18,c19)

        categories.value = list

    }

}