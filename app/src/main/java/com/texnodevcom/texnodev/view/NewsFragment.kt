package com.texnodevcom.texnodev.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.adapter.NewsAdapter
import com.texnodevcom.texnodev.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*


class NewsFragment : Fragment() {

    private lateinit var viewModel : NewsViewModel
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_news, container, false)
//        view.newsRecyclerView.showShimmer()
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.refreshData()

        newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView.adapter = newsAdapter

        swipeRefreshLayout.setOnRefreshListener {
            newsRecyclerView.visibility = View.GONE
            newsError.visibility = View.GONE
            newsLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.postList.observe(viewLifecycleOwner, Observer {posts ->

            posts?.let {
                newsRecyclerView.visibility = View.VISIBLE
                newsAdapter.updateCountryList(posts)
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
                    newsRecyclerView.visibility = View.GONE
                    newsError.visibility = View.GONE
                } else {
                    newsLoading.visibility = View.GONE
                }
            }
        })
    }

//    private fun showShimmerEffect(){
//        mView.newsRecyclerView.showShimmer()
//    }
//
//    private fun hideShimmerEffect(){
//        mView.newsRecyclerView.hideShimmer()
//    }

}