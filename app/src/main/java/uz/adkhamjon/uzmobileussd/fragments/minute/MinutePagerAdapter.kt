package uz.adkhamjon.uzmobileussd.fragments.minute

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.adkhamjon.uzmobileussd.fragments.internet.InternetPagerFragment

class MinutePagerAdapter(private val list: ArrayList<String>, fm: FragmentManager):
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return MinutePagerFragment.newInstance(list[position])
    }
}