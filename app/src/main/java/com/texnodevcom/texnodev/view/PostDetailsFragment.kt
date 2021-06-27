package com.texnodevcom.texnodev.view

import android.content.ContentValues
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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.getDateTimeDetails
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.texnodevcom.texnodev.R
import com.texnodevcom.texnodev.databinding.FragmentPostDetailsBinding
import com.texnodevcom.texnodev.model.Post
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

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var mAdView : AdView

//    private lateinit var image: ImageView
    private lateinit var conn: Document
    private val list = ArrayList<String>()
    private val list2 = ArrayList<String>()
    private lateinit var x: Elements
    private var check: Boolean? = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBannerAds()
//        goToSecond()

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        val id = argPostID.postID
        check = argPostID.favCheck
        viewModel.getPostByID(id)

        observerLiveDataPost()
    }

    private fun observerLiveDataPost(){
        viewModel.postLiveData.observe(viewLifecycleOwner, Observer { post ->
            post?.let {
                binding.post = it
                detailsDate.text = getDateTimeDetails(post.date.toString())
                getHtml(post.content.toString())

                if (check == true){
                    detailsFavorite.isChecked = true
                    detailsFavorite.setButtonDrawable(R.drawable.turned_in_texnodev)
                    onCheckFavoritePost(post)
                }else{
                    onCheckFavoritePost(post)
                }

            }
        })
    }

    fun onCheckFavoritePost(post: Post){
        detailsFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (detailsFavorite.isChecked){
                viewModel.insertFAV(post)
                detailsFavorite.setButtonDrawable(R.drawable.turned_in_texnodev)
                Toast.makeText(requireContext(), "Favoritə əlavə olund.", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.deleteFavoriteByID(post.id!!.toInt())
                detailsFavorite.setButtonDrawable(R.drawable.turned_in_not_favorite)
                Toast.makeText(requireContext(), "Favoritlər-dən silindi.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showBannerAds(){
        //Banner ID
        //ca-app-pub-7752518464363066/9541659272
        //Test ID ca-app-pub-3940256099942544/6300978111
        MobileAds.initialize(requireContext()) {}
        mAdView = adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    private fun goToSecond(){
        //NewsFragment kecid AdMob rekalm ID
        //original ID  ca-app-pub-7752518464363066/3939701007
        // test ID  ca-app-pub-3940256099942544/1033173712
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(),"ca-app-pub-3940256099942544/1033173712",
            adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(ContentValues.TAG, adError.message)
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(ContentValues.TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
//        if (rowPostLayout.isClickable){
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(requireActivity())
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
//        }
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
//        lp.setMargins(10, 5, 10, 5)
        img.setLayoutParams(lp)
//        img.marginTop
//        img.marginBottom
//        img.marginEnd
//        img.marginLeft
        img.setImageResource(R.drawable.ic_launcher_background)
        img.getLayoutParams().height = 675;
        img.getLayoutParams().width = 1050;

        img.downloadFromUrl(url, placeholderProgressBar(requireContext()))
        detailsLinearLayout.addView(img)
    }

    fun textvieww(text: String?) {
        val textView = TextView(requireContext())
        textView.textSize = 17.5F
//        textView.setPadding(5)
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.detailsContentColor))
//        textView.setPadding(5)
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