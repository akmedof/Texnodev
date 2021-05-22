package com.texnodevcom.texnodev.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.getDateTimeDetails
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.databinding.FragmentPostDetailsBinding
import com.texnodevcom.texnodev.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_post_details.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class PostDetailsFragment : Fragment() {

    private val argPostID by navArgs<PostDetailsFragmentArgs>()
    private lateinit var viewModel : DetailsViewModel
    private lateinit var binding : FragmentPostDetailsBinding

    private lateinit var image: ImageView
    private lateinit var conn: Document
    private val list = ArrayList<String>()
    private val list2 = ArrayList<String>()
    private lateinit var x: Elements


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_details, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val id = argPostID.postID

        viewModel.getPostByID(id)

        viewModel.postLiveData.observe(viewLifecycleOwner, Observer { post ->
            post?.let {
                binding.post = it
                detailsDate.text = getDateTimeDetails(post.date.toString())
                    getHtml(post.content.toString())
            }
        })
    }

    fun adddata() {
        for (l in list) {
            if (l.substring(0, 8) == "https://") {
                img(l)
            } else {
                textvieww(l)
            }
        }
    }


    fun img(url: String?) {
        val img = ImageView(requireContext())
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10, 5, 10, 5)
        img.setLayoutParams(lp)
        img.marginTop
        img.marginBottom
        img.marginEnd
        img.marginLeft
        img.setImageResource(R.drawable.ic_launcher_background)
        img.getLayoutParams().height = 1000;
        img.getLayoutParams().width = 1000;

        img.downloadFromUrl(url, placeholderProgressBar(requireContext()))
        detailsLinearLayout.addView(img)
    }

    fun textvieww(text: String?) {
        val textView = TextView(requireContext())
        textView.textSize = 17.5F
        textView.setPadding(5)
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.detailsContentColor))
        textView.setPadding(5)
        textView.text = text
        detailsLinearLayout.addView(textView)
    }

    fun getHtml(html: String) {
        try {
            conn = Jsoup.parse(html)
            x = conn.select("img")
            val y: Elements = conn.select("p,img")
            val img = "<img src"
            for (k in y) {
                if (k.toString().substring(0, 8) == img) {
                    list.add(k.attr("src"))
                } else {
                    list.add(k.text())
                }
                list2.add(k.toString())
            }
            adddata()
        }catch (e: Exception){

        }
    }



}