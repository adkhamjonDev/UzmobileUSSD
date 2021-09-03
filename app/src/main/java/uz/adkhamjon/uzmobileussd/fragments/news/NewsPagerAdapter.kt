package uz.adkhamjon.uzmobileussd.fragments.news
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NewsPagerAdapter(private val list: ArrayList<String>, fm: FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NewsPagerFragment.newInstance(list[position])
            }
            1 -> {
                NewsPagerFragment.newInstance(list[position])
            }
            else -> NewsPagerFragment()
        }
    }
}