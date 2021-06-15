package com.texnodevcom.texnodev.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.adapter.CategoryAdapter
import com.texnodevcom.texnodev.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {

    private lateinit var viewModel : CategoryViewModel
    private val categoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        viewModel.getData()

        categoryRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2,)
        categoryRecyclerView.adapter = categoryAdapter

        viewModel.categories.observe(viewLifecycleOwner, Observer { cates->
            cates?.let {
                categoryAdapter.updateCategoryList(it)
            }
        })

    }


}