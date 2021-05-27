package com.texnodevcom.texnodev.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.adapter.FavoriteAdapter
import com.texnodevcom.texnodev.model.Favorite
import com.texnodevcom.texnodev.viewmodel.FavoriteViewModel
import com.texnodevmedia.texnodev.dao.PostDatabase
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.post_item_row.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private val adapter = FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.getAllFavorite()

        recyclerViewFav.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFav.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner, Observer { favorites->
            favorites?.let {
                adapter.updateFavoriteList(it)
            }
        })


    }

}