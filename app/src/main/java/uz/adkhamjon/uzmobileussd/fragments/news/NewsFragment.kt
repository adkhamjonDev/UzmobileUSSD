package uz.adkhamjon.uzmobileussd.fragments.news

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.databinding.DefaultItemBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentNewsBinding
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val binding by viewBinding(FragmentNewsBinding::bind)
    private lateinit var bind: DefaultItemBinding


    private lateinit var categoryList:ArrayList<Int>
    private lateinit var categoryList1:ArrayList<String>

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var newsPagerAdapter:NewsPagerAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)





        categoryList=ArrayList()
        categoryList.add(R.string.news_tab)
        categoryList.add(R.string.sale_tab)
        categoryList1=ArrayList()
        categoryList1.add("Yangiliklar")
        categoryList1.add("Aksiyalar")
        newsPagerAdapter= NewsPagerAdapter(categoryList1, childFragmentManager)


        binding.apply {
            binding.viewPager.adapter=newsPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
            setTabs()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val linearLayout = tabView!!.findViewById<LinearLayout>(R.id.linear_layout)
                    val tittle = tabView.findViewById<TextView>(R.id.tittle_item)
                    linearLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.default_tab2)
                    tittle.setTextColor(Color.parseColor("#01B4FF"))
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val linearLayout = tabView!!.findViewById<LinearLayout>(R.id.linear_layout)
                    val tittle = tabView.findViewById<TextView>(R.id.tittle_item)
                    linearLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.default_tab)
                    tittle.setTextColor(Color.WHITE)
                }
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

        }
    }

    private fun setTabs() {
        val count: Int = binding.tabLayout.tabCount
        for (i in 0 until count) {
            bind = DefaultItemBinding.inflate(layoutInflater, null, false)
            bind.tittleItem.setText(categoryList[i])
            if (i == 0) {
                bind.linearLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.default_tab2)
                bind.tittleItem.setTextColor(Color.parseColor("#01B4FF"))
            } else {
                bind.linearLayout.background = ContextCompat.getDrawable(requireContext(),R.drawable.default_tab)
                bind.tittleItem.setTextColor(Color.WHITE)
            }
            binding.tabLayout.getTabAt(i)?.customView = bind.root
        }
    }
}