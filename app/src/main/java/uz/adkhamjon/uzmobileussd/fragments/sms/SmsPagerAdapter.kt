package uz.adkhamjon.uzmobileussd.fragments.sms
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SmsPagerAdapter(private val list: ArrayList<String>, fm: FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SmsPagerFragment.newInstance(list[position])
            }
            1 -> {
                SmsPagerFragment.newInstance(list[position])
            }
            2 -> {
                SmsPagerFragment.newInstance(list[position])
            }
            3 -> {
            SmsPagerFragment.newInstance(list[position])
        }
            else -> SmsPagerFragment()
        }
    }
}