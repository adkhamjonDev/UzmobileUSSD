package uz.adkhamjon.uzmobileussd.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import uz.adkhamjon.uzmobileussd.R

class  BannerPagerAdapter(val context: Context, private val list: List<String>): PagerAdapter() {
    private lateinit var layoutInflater: LayoutInflater
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater= LayoutInflater.from(context)
        val view=layoutInflater.inflate(R.layout.view_pager_item,container,false)

        val imageView:ImageView = view.findViewById(R.id.image)
        Glide.with(context).load(list[position]).into(imageView)
        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }
}