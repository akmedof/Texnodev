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
    val html = "<!-- wp:paragraph -->\n<p>Bu günlərdə görüntü texnologiyaları inkişaf etdikcə hər gün yeni bir rezolyusiya formatı görünməyə başladı. HD, Full HD, 2K, 4K dedikdə 8K rezolyusiyadan danışdıq. Bəs bütün bunlar nə deməkdir? Sizə məlum olan bütün rezolyusiya formatlarını araşdırdıq və fərqlərin nələr olduğu sualına ətraflı cavab verdik.<br>Texnologiyanın inkişafı ilə gördüyümüz görüntülər daha realdır və eşitdiyimiz səslər yanımızda olduğumuz kimi eşidilir. Bu vəziyyət həyəcan verici olsa da, son dərəcə qarışıq olduğu etiraf edilməlidir. Artıq HD, Full HD, 2K, 4K və ya hətta 8K rezolyusiya formatları mövcuddur və əksər istifadəçilər HD, Full HD, 2K, 4K və 8K arasında hansı formatı seçəcəyini bilmirlər.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p><strong>Əsaslardan başlayaq; Rezolyusiya nədir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>Görüntü imkanı; Hər hansı bir rəqəmsal cihazın ekrandakı ölçüsü başına piksel sayına aiddir. Çözünürlüyü əllə hörülmüş bir paltar kimi düşünə bilərik. Paltar nə qədər möhkəm toxunsa, geyimi o qədər isti saxlayır. Piksel sayı nə qədər çox olarsa, ortaya çıxan görüntü o qədər real və keyfiyyətli olacaqdır.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p><strong>720p (HD) nədir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>Qısaca HD adlandırılan 720p, yüksək dəqiqlikli rəqəmsal rezolyusiya formatı, illərdir həyatımızda olan məşhur formatlardan biridir. Piksel dəyəri 1280 × 720 olaraq göstərilir. 1280 × 720 piksel nisbəti üfüqi və şaquli piksellərə aiddir. Beləliklə, HD çözünürlüklü bir ekranda 1280 üfüqi piksel və 720 şaquli piksel görəcəksiniz.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:image {\"id\":3143,\"sizeSlug\":\"large\"} -->\n<figure class=\"wp-block-image size-large\"><img src=\"https://texnodev.com/wp-content/uploads/2021/05/hd-1024x576.jpeg\" alt=\"\" class=\"wp-image-3143\"/></figure>\n<!-- /wp:image -->\n\n<!-- wp:paragraph -->\n<p><strong>1080p (Full HD) nədir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>1080p, Full High-definition uzun müddətdir istifadə olunan formatlardan biridir, qısaca Full HD adlanan rəqəmsal rezolyusiya formatıdır. Piksel dəyəri 1920 × 1080 olaraq göstərilir. 1920 × 1080 piksel nisbəti yatay və şaquli piksellərə aiddir. Beləliklə Full HD rezolyusiyalı bir ekranda 1920 yatay piksel və 1080 şaquli piksel görürsünüz.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>Artıq izləyiciyə orta hesabla 2 milyon piksel təklif edən 1080p Full HD rezolyusiya formatında bir canlı yayımı demək olar ki, bütün televizorlarda, kompüterlərdə, planşetlərdə, smartfonlarda və digər bir çox cihazda asanlıqla izləyə bilərsiniz.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:image {\"id\":3144,\"sizeSlug\":\"large\"} -->\n<figure class=\"wp-block-image size-large\"><img src=\"https://texnodev.com/wp-content/uploads/2021/05/full-hd-1024x534.jpeg\" alt=\"\" class=\"wp-image-3144\"/></figure>\n<!-- /wp:image -->\n\n<!-- wp:paragraph -->\n<p><strong>2K nədir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>2K bir kino rezolyusiya formatıdır. Rəqəmsal kino proyektorlarında gördüyümüz 2K formatının piksel dəyəri 2048 x 1080 olaraq göstərilir. Beləliklə demək olar ki, Full HD ilə eynidir. Full HD ilə 2K arasındakı fərq, Full HD'nin son istehlakçının evinə gələn televiziyalarda istifadə olunan rezolyusiya formatının olmasıdır.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:image {\"id\":3145,\"sizeSlug\":\"large\"} -->\n<figure class=\"wp-block-image size-large\"><img src=\"https://texnodev.com/wp-content/uploads/2021/05/2k-1024x576.jpeg\" alt=\"\" class=\"wp-image-3145\"/></figure>\n<!-- /wp:image -->\n\n<!-- wp:paragraph -->\n<p><strong>4K nədir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>4K rəqəmsal rezolyusiya formatıdır. Piksel dəyəri 3840 x 2160 olaraq göstərilir. 3840 x 2160 piksel nisbəti üfüqi və şaquli piksellərə aiddir. Beləliklə, 4K rezolyusiyalı bir ekranda 3840 üfüqi piksel və 2160 şaquli piksel görəcəksiniz. 4K rezolyusiya formatı getdikcə populyarlaşan və tanınan bir formatdır.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>İzləyiciyə orta hesabla 8,3 milyon piksel təklif edən 4K rezolyusiya formatı, adını yatay olaraq demək olar ki, 4 min piksel təklif etdiyi üçün İngilis dilində \"min\" mənasını verən \"K\" nin istifadəsindən irəli gəlir. Tam 4 min piksel təklif etməsə də, demək olar ki, 4 min təklif etdiyi üçün bu adı ticari bir hərəkət olaraq aldı.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:image {\"id\":3146,\"sizeSlug\":\"large\"} -->\n<figure class=\"wp-block-image size-large\"><img src=\"https://texnodev.com/wp-content/uploads/2021/05/4k-1024x576.jpeg\" alt=\"\" class=\"wp-image-3146\"/></figure>\n<!-- /wp:image -->\n\n<!-- wp:paragraph -->\n<p><strong>8K nədir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>8K rəqəmsal rezolyusiya formatıdır. Piksel dəyəri 7680 x 4320 olaraq göstərilir. 7680 x 4320 piksel nisbəti üfüqi və şaquli piksellərə aiddir. Beləliklə, 8K rezolyusiyalı bir ekranda 7680 üfüqi piksel və 4320 şaquli piksel görəcəksiniz. 8K rezolyusiyalı televizorlar istifadəçilərə təklif edilsə də, 4K hələ tam yayılmamışdır və 8K televizor almaq lazım hesab edilmir.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>İzləyiciyə ortalama 33 milyon piksel təklif edən 8K rezolyusiya formatı, adını üfüqi olaraq 8 min piksel təklif etdiyi üçün adını \"min\" mənasını verən \"K\" istifadəsindən götürür. Tam 8 min piksel təklif etməsə də, bu adı demək olar ki, 8 min təklif etdiyi üçün ticari bir hərəkət olaraq aldı.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:image {\"id\":3147,\"sizeSlug\":\"large\"} -->\n<figure class=\"wp-block-image size-large\"><img src=\"https://texnodev.com/wp-content/uploads/2021/05/8k-1024x576.jpeg\" alt=\"\" class=\"wp-image-3147\"/></figure>\n<!-- /wp:image -->\n\n<!-- wp:paragraph -->\n<p><strong>Xülasə edək: HD, Full HD, 2K, 4K və 8K arasında fərqlər nələrdir?</strong></p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>Rezolyusiya formatlarını tək-tək işlədikdən sonra toplamaq lazımdırsa; Artıq demək olar ki, hər hansı bir cihazda HD və Full HD rezolyusiya formatlarını çəkə bilərsiniz. Yeni bir cihaz satın alırsınızsa, HD və Full HD ilə uyğun olduğundan əmin olmalısınız.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>2K kino rezolyusiya formatı olduğundan, son istehlakçı kimi aldığınız cihazları axtarmağa ehtiyac yoxdur. Bunun əvəzinə Full HD-nin uyğunluğunu şübhə altına alın. 4K getdikcə yayılan bir formatdır, buna görə yeni bir televizor alırsınızsa və uzun müddət istifadə etmək istəyirsinizsə, 4K ilə uyğunlaşmağınız daha yaxşı bir seçim olardı.</p>\n<!-- /wp:paragraph -->\n\n<!-- wp:paragraph -->\n<p>8K hazırda son dərəcə məhdud istifadəsi olan bir qətnamə formatıdır. Bir neçə eksperimental axın xaricində 8K izləmək üçün çox səbəb yoxdur. Bu səbəbdən 8K, gələcəkdə həvəslə izləməyimizi gözlədiyimiz, lakin bugünkü düşüncəmizi narahat etməməli bir sistemdir.</p>\n<!-- /wp:paragraph -->"


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
//            Log.i("aaa", l.substring(0, 8))
            if (l.substring(0, 8) == "https://") {
//                Log.d("urr", l)
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