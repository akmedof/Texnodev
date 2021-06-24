package com.texnodevcom.texnodev.view

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.adapter.NewsAdapter
import com.texnodevcom.texnodev.util.ConnectionLiveData
import com.texnodevcom.texnodev.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.android.synthetic.main.fragment_post_details.*
import kotlinx.android.synthetic.main.post_item_row.*
import kotlinx.android.synthetic.main.post_item_row.view.*


class NewsFragment : Fragment() {

    private lateinit var viewModel : NewsViewModel
    private val newsAdapter = NewsAdapter()
    private lateinit var mView : View
//    private lateinit var cld : ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_news, container, false)
//        view.newsRecyclerView.showShimmer()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.refreshData()
//        checkNetworkConnected()


        newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        newsRecyclerView.adapter = newsAdapter

        swipeRefreshLayout.setOnRefreshListener {
            newsError.visibility = View.GONE
            refresDataID.visibility =View.GONE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        refresDataID.setOnClickListener {
            newsError.visibility = View.GONE
            refresDataID.visibility = View.GONE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.postList.observe(viewLifecycleOwner, Observer {posts ->

            posts?.let {
                newsRecyclerView.visibility = View.VISIBLE
                newsAdapter.updatePostList(posts)
                hideShimmerEffect()
            }

        })

        viewModel.postError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it) {
                    refresDataID.visibility = View.VISIBLE
                    newsError.visibility = View.GONE
                } else {
                    newsError.visibility = View.GONE
                    refresDataID.visibility = View.GONE
                }
            }
        })

        viewModel.postLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    showShimmerEffect()
                    newsError.visibility = View.GONE
                } else {
//                    newsLoading.visibility = View.GONE
                    hideShimmerEffect()
                }
            }
        })
    }

    private fun showShimmerEffect(){
        mView.newsRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect(){
        mView.newsRecyclerView.hideShimmer()
    }

//    private fun checkNetworkConnected(){
//        val application = Application()
//        cld = ConnectionLiveData(application)
//
//        cld.observe(viewLifecycleOwner, Observer { isConnected ->
//            if (isConnected){
//                viewModel.refreshData()
//            }else{
//                viewModel.refreshFromRoom()
//            }
//        })
//    }

}