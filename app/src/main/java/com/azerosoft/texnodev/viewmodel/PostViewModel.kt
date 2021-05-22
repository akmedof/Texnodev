package com.azerosoft.texnodev.viewmodel

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.azerosoft.texnodev.model.Post
import com.azerosoft.texnodev.services.PostAPIServices
//import com.azerosoft.texnodev.util.CustomSharedPereferances
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postAPIServices: PostAPIServices,
    application: Application
) : BaseViewModel(application) {

    //private val postAPIServices = PostAPIServices()
    private val disposable = CompositeDisposable()
//    private val customSharedPereferances = CustomSharedPereferances(getApplication())
    private var refresTime = 10 * 60 * 1000 * 1000 * 1000L

    val posts = MutableLiveData<List<Post>>()
    val postError = MutableLiveData<Boolean>()
    val postLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
//        val updateTime = customSharedPereferances.getTime()
//        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refresTime){
//            getDataFromSQLite()
//        }else{
//            getDataFromAPI()
//        }
    }

//    private fun getDataFromSQLite(){
//        launch {
//            val postList = PostDatabase(getApplication()).postDAO().getAllPosts()
//            showPosts(postList)
//            Toast.makeText(getApplication(), "Post in SQLite", Toast.LENGTH_LONG).show()
//        }
//    }

    private fun getDataFromAPI(){
        postLoading.value = true
        disposable.add(
            postAPIServices.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Post>>(){
                    override fun onSuccess(t: List<Post>) {
//                        storeInSQLite(t)
                        showPosts(t)
                        Toast.makeText(getApplication(), "Post in API", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        postLoading.value = false
                        postError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showPosts(list: List<Post>){
        posts.value = list
        postError.value = false
        postLoading.value = false
    }

//    private fun storeInSQLite(list: List<Post>){
//        launch {
//            val dao = PostDatabase(getApplication()).postDAO()
//            dao.deleteALLPosts()
//            val listLong = dao.insertAll(*list.toTypedArray())
//            var i = 0
//            while (i < list.size){
//                list[i].uuid = listLong[i].toInt()
//                i += 1
//            }
//            showPosts(list)
//        }
//        customSharedPereferances.saveTime(System.nanoTime())
//    }

}