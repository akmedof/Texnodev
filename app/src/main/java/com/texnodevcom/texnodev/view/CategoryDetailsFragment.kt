package com.texnodevcom.texnodev.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category_details.*

class CategoryDetailsFragment : Fragment() {

    private val categoryArgsID by navArgs<CategoryDetailsFragmentArgs>()
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = categoryArgsID.categoryID
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.getData()


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
                    }
                }
            }
        })
    }
}