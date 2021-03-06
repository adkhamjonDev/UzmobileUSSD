package uz.adkhamjon.uzmobileussd.fragments.internet

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity.type
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.databinding.DefaultItemBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import java.lang.reflect.Array


class InternetFragment : Fragment() {
    private lateinit var binding:FragmentInternetBinding
    private lateinit var bind: DefaultItemBinding

    private lateinit var internetPagerAdapter: InternetPagerAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference



    private lateinit var type:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentInternetBinding.inflate(inflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)

        type= ArrayList()
//        reference.child("internet/internetTypes").addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                snapshot.children.forEach {
//                    val str=it.getValue(String::class.java)
//                    if (str != null) {
//                        type.add(str)
//
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
        val stringArray = resources.getStringArray(R.array.paket)
        stringArray.forEach {
            type.add(it)
        }



        internetPagerAdapter= InternetPagerAdapter(type,childFragmentManager)
        binding.viewPager.adapter=internetPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setTabs()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

        binding.checkTarif.setOnClickListener {
            Config.run(requireContext(),"*105#")
        }
        return binding.root
    }
    private fun setTabs() {
        val count: Int = binding.tabLayout.tabCount
        for (i in 0 until count) {
            bind = DefaultItemBinding.inflate(layoutInflater, null, false)
            val s = type[i]
            bind.tittleItem.text = s
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