package com.texnodevcom.texnodev.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.model.Post
import com.texnodevcom.texnodev.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

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
        println("POST ID: " + uuid)
        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel.getPostByID(uuid)

        val post = Observer<Post>{ post ->
            detailsTitle.text = post.title
            detailsDate.text = post.date
            detailsContent.text = post.content
        }

        viewModel.postLiveData.observe(this, post)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

