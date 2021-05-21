package com.texnodevcom.texnodev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.navArgs
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import org.jsoup.Jsoup

class DetailsActivity : AppCompatActivity() {


    private val argPostID by navArgs<DetailsActivityArgs>()
    private lateinit var viewModel : DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolBar)
        toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val uuid = argPostID.postUUID

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.getPostByID(uuid)

        observeLiveData()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeLiveData(){
        val post = Observer<Post>{ post ->
            detailsTitle.text = post.title
            detailsDate.text = post.date
            detailsContent.text = Jsoup.parse(post.content).text()
            detailsImage.downloadFromUrl(post.postImage, placeholderProgressBar(applicationContext))
        }

        viewModel.postLiveData.observe(this, post)
    }
}

