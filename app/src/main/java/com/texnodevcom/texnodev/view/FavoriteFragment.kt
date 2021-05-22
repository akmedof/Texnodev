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
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.atilsamancioglu.kotlincountries.util.downloadFromUrl
import com.atilsamancioglu.kotlincountries.util.placeholderProgressBar
import com.texnodevcom.texnodev.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class FavoriteFragment : Fragment() {

    private lateinit var image: ImageView
    private lateinit var conn: Document
    private val list = ArrayList<String>()
    private lateinit var x: Elements
    //    private lateinit var linearLayout: LinearLayout
    val code =  "https://"
    val html = "<!-- wp:paragraph -->\\n<p>Mart ayında Qualcomm 5nm Snapdragon 780G çipsetini gətirdi və indi Snapdragon 778G adlanan bir az tonlu versiyasını gətirir. Əsas fərq, 778G'nin 780G-də görünən Samsungun 5nm əvəzinə TSMC-nin 6nm nodu üzərində qurulmasıdır.</p>\\n<!-- /wp:paragraph -->\\n\\n<!-- wp:image {\\\"id\\\":3184,\\\"width\\\":450,\\\"height\\\":452,\\\"sizeSlug\\\":\\\"large\\\"} -->\\n<figure class=\\\"wp-block-image size-large is-resized\\\"><img src=\\\"https://texnodev.com/wp-content/uploads/2021/05/gsmarena_003.jpg\\\" alt=\\\"\\\" class=\\\"wp-image-3184\\\" width=\\\"450\\\" height=\\\"452\\\"/></figure>\\n<!-- /wp:image -->\\n\\n<!-- wp:paragraph -->\\n<p>Yeni 778G, Adreno 642L GPU və Spectra 570L ISP ilə qoşulmuş bir Kryo 670 CPU gətirir. Çipset ayrıca mmWave və alt-6 5G əlaqə dəstəyi təmin edən inteqrasiya olunmuş Snapdragon X53 5G Modem-RF gətirir. Qualcomm’un üçqat İnternet provayderi eyni zamanda geniş, ultra geniş və telefoto kameralardan foto və video çəkməyə imkan verir.</p>\\n<!-- /wp:paragraph -->\\n\\n<!-- wp:paragraph -->\\n<p>Snapdragon 778G’i bu il Honor, iQOO, Motorola, Oppo, Realme və Xiaomi’dən orta səviyyəli təkliflərdə gözləyin.</p>\\n<!-- /wp:paragraph -->"

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
        imgTest.downloadFromUrl("https://texnodev.com/wp-content/uploads/2021/05/full-hd-1024x534.jpeg",
        placeholderProgressBar(requireContext()))
        getHtml(html)
    }

    fun adddata() {
        for (l in list) {
//            l.substring(0, 8)
            Log.d("asdqwe", l)
            Log.i("aaa", l.substring(0, 8))
            if (l.substring(0, 8) == "https://") {
                Log.d("aaa", l)
                img(l)
            } else {
                textvieww(l)
            }
        }
    }


    fun img(url: String?) {
        val img = ImageView(requireContext())
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(10, 10, 10, 10)
        img.setLayoutParams(lp)
        img.marginTop
        img.marginBottom
        img.marginEnd
        img.marginLeft
        img.setImageResource(R.drawable.ic_launcher_background)
        img.getLayoutParams().height = 1000;
        img.getLayoutParams().width = 1000;

        img.downloadFromUrl(url, placeholderProgressBar(requireContext()))
        linLay.addView(img)
    }

    fun textvieww(text: String?) {
        val textView = TextView(requireContext())
        textView.textSize = 20F
        textView.text = text
        linLay.addView(textView)
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
            }
            adddata()
        }catch (e: Exception){

        }
    }


}