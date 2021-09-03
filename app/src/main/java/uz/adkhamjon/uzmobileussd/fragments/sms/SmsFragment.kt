package uz.adkhamjon.uzmobileussd.fragments.sms

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
import uz.adkhamjon.uzmobileussd.adapters.MinuteAdapter
import uz.adkhamjon.uzmobileussd.adapters.SmsAdapter
import uz.adkhamjon.uzmobileussd.databinding.DefaultItemBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentMinuteBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentSmsBinding
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

class SmsFragment : Fragment(R.layout.fragment_sms) {
    private val binding by viewBinding(FragmentSmsBinding::bind)
    private lateinit var bind: DefaultItemBinding
    private lateinit var categoryList:ArrayList<String>

    private lateinit var list:ArrayList<SmsModel>
    private lateinit var list1:ArrayList<SmsModel>
    private lateinit var list2:ArrayList<SmsModel>
    private lateinit var list3:ArrayList<SmsModel>

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var smsPagerAdapter: SmsPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)

        if(SharedPreference.getInstance(requireContext()).lang=="uzbek"){
            categoryList= ArrayList()
            categoryList.add("AKunlik SMS paketlar")
            categoryList.add("BOylik SMS paketlar")
            categoryList.add("CXalqaro SMS paketlar")
            categoryList.add("D\"Constructor\" TR abonentlari uchun SMS paketlar")
        }
        else if(SharedPreference.getInstance(requireContext()).lang=="latin"){
            categoryList= ArrayList()
            categoryList.add("AКунлик СМС пакетлар")
            categoryList.add("BОйлик СМС пакетлар")
            categoryList.add("CХалкаро СМС пакетлар")
            categoryList.add("D\"Constructor\" ТР абонентлари учун СМС пакетлар")
        }
        else if(SharedPreference.getInstance(requireContext()).lang=="russian"){
            categoryList= ArrayList()
            categoryList.add("AЕжедневные СМС пакеты")
            categoryList.add("BЕжемесячные СМС пакеты")
            categoryList.add("CМеждународные СМС пакеты")
            categoryList.add("DСМС пакеты для абонентов ТП \"Constructor\"")
        }
        smsPagerAdapter= SmsPagerAdapter(categoryList,childFragmentManager)
        binding.apply {
            binding.viewPager.adapter=smsPagerAdapter
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

            checkTarif.setOnClickListener {
                Config.run(requireContext(),"*100*2#")
            }
        }

    }
    private fun setTabs() {
        val count: Int = binding.tabLayout.tabCount
        for (i in 0 until count) {
            bind = DefaultItemBinding.inflate(layoutInflater, null, false)
            var s = categoryList[i]
            s=s.substring(1)
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