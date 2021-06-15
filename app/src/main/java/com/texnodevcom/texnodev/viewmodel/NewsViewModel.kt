package com.texnodevcom.texnodev.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.atilsamancioglu.kotlincountries.util.CustomSharedPreferences
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.services.NewsApiServices
import com.texnodevmedia.texnodev.dao.PostDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : BaseViewModel(application) {

    private val newsApiServices = NewsApiServices()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val postList = MutableLiveData<List<Post>>()
    val postError = MutableLiveData<Boolean>()
    val postLoading = MutableLiveData<Boolean>()

    fun refreshData() {

        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }

    }

    fun refreshFromAPI() {
        getDataFromAPI()
    }

    private fun getDataFromSQLite() {
        postLoading.value = true
        launch {
            val posts = PostDatabase(getApplication()).postDAO.getAllPosts()
            showNews(posts)
            Toast.makeText(getApplication(),"News Post From SQLite", Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI() {
        postLoading.value = true
        disposable.add(
            newsApiServices.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Post>>(){
                    override fun onSuccess(t: List<Post>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"News Post From API", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        postLoading.value = false
                        postError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showNews(countryList: List<Post>) {
        postList.value = countryList
        postError.value = false
        postLoading.value = false
    }

    private fun storeInSQLite(list: List<Post>) {
        launch {
            val dao = PostDatabase(getApplication()).postDAO
            dao.deleteAllPosts()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }

            showNews(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

}