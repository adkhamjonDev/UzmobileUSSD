package uz.adkhamjon.uzmobileussd.fragments.internet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class InternetPagerAdapter(private val list: ArrayList<String>, fm: FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return InternetPagerFragment.newInstance(list[position])
    }
}