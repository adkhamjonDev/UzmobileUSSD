package uz.adkhamjon.uzmobileussd.fragments.minute

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.adapters.MinuteAdapter
import uz.adkhamjon.uzmobileussd.databinding.DefaultItemBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentMinuteBinding
import uz.adkhamjon.uzmobileussd.fragments.internet.InternetPagerAdapter
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference


class MinuteFragment : Fragment(R.layout.fragment_minute) {
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var bind: DefaultItemBinding
    private lateinit var reference: DatabaseReference
    private val binding by viewBinding(FragmentMinuteBinding::bind)
    private lateinit var minuteAdapter: MinuteAdapter
    private lateinit var list:ArrayList<MinuteModel>
    private lateinit var type:ArrayList<String>
    private lateinit var minutePagerAdapter: MinutePagerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)


        type= ArrayList()
        val stringArray = resources.getStringArray(R.array.minute)
        stringArray.forEach {
            type.add(it)
        }
        minutePagerAdapter= MinutePagerAdapter(type,childFragmentManager)

        binding.apply {

            binding.viewPager.adapter = minutePagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
            setTabs()
            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val linearLayout = tabView!!.findViewById<LinearLayout>(R.id.linear_layout)
                    val tittle = tabView.findViewById<TextView>(R.id.tittle_item)
                    linearLayout.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.default_tab2)
                    tittle.setTextColor(Color.parseColor("#01B4FF"))
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val tabView = tab.customView
                    val linearLayout = tabView!!.findViewById<LinearLayout>(R.id.linear_layout)
                    val tittle = tabView.findViewById<TextView>(R.id.tittle_item)
                    linearLayout.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.default_tab)
                    tittle.setTextColor(Color.WHITE)
                }

                override fun onTabReselected(tab: TabLayout.Tab) {}
            })

            binding.checkTarif.setOnClickListener {
                Config.run(requireContext(), "*105#")
            }
        }

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