package com.azerosoft.texnodev.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.azerosoft.texnodev.adapter.PostAdapter
import com.azerosoft.texnodev.viewmodel.PostViewModel
import com.azerosoft.texnodev.R
import com.azerosoft.texnodev.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private lateinit var viewModel : PostViewModel
    private val postAdapter = PostAdapter(arrayListOf())
    private val mAdapter by lazy { NewsAdapter() }
    private lateinit var mView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_news, container, false)

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        viewModel.refreshData()

        recyclerViewNews.layoutManager = LinearLayoutManager(context)
        recyclerViewNews.adapter = mAdapter

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.posts.observe(viewLifecycleOwner, Observer {posts ->

            posts?.let {
                recyclerViewNews.visibility = View.VISIBLE
                mAdapter.setData(posts)
            }

        })

        viewModel.postError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it) {
                    newsError.visibility = View.VISIBLE
                } else {
                    newsError.visibility = View.GONE
                }
            }
        })

        viewModel.postLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    newsLoading.visibility = View.VISIBLE
                    recyclerViewNews.visibility = View.GONE
                    newsError.visibility = View.GONE
                } else {
                    newsLoading.visibility = View.GONE
                }
            }
        })
    }

    private fun showShimmerEffect(){
        mView.recyclerViewNews.showShimmer()
    }

    private fun hideShimmerEffect(){
        mView.recyclerViewNews.hideShimmer()
    }

}