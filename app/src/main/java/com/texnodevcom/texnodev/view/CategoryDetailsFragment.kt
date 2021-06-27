package com.texnodevcom.texnodev.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.adapter.CategoryDetailsAdapter
import com.texnodevcom.texnodev.viewmodel.CategoryDetailsViewModel
import com.texnodevcom.texnodev.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category_details.*
import kotlinx.android.synthetic.main.fragment_category_details.view.*
import kotlinx.android.synthetic.main.fragment_favorite.*

class CategoryDetailsFragment : Fragment() {

    private val categoryArgsID by navArgs<CategoryDetailsFragmentArgs>()
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var viewModel: CategoryDetailsViewModel
    private lateinit var mView : View
    private val adapter = CategoryDetailsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_category_details, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MobileAds.initialize(requireContext()) {}
        var mAdView = adCategoryDetailsID
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val id = categoryArgsID.categoryID
        viewModel = ViewModelProvider(this).get(CategoryDetailsViewModel::class.java)
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.getData()

//        favDetSwipeRefreshID.setOnRefreshListener {
//            categoryViewModel.getData()
//            favSwipeRefresh.isRefreshing = false
//        }

        recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCategory.adapter = adapter

        categoryObserver(id)
    }

    private fun categoryObserver(id: Int){
        categoryViewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                categories.forEach {
                    if (it.id == id){
                        categoryDetailsBG.setImageResource(it.bg)
                        categoryDetailsIcon.setImageResource(it.icon)
                        categoryDetailsText.text = it.name
                        categoryList(it.name)
                        hideShimmerEffect()
                    }
                }
            }
        })
    }

    private fun categoryList(name: String){
        viewModel.getData(name)
        viewModel.posts.observe(viewLifecycleOwner, Observer { posts->
            posts?.let {
                if (it.isNotEmpty()){
                    adapter.setData(it)
                }else{
                    favDetImageID.visibility = View.VISIBLE
                    favDetImageTextID.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showShimmerEffect(){
        mView.recyclerViewCategory.showShimmer()
    }

    private fun hideShimmerEffect(){
        mView.recyclerViewCategory.hideShimmer()
    }
}