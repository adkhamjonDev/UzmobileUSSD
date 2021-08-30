package uz.adkhamjon.uzmobileussd.fragments.internet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding


class InternetFragment : Fragment(R.layout.fragment_internet) {
    private val binding by viewBinding(FragmentInternetBinding::bind)
    private lateinit var internetAdapter: InternetAdapter
    private lateinit var list:ArrayList<Int>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        list= ArrayList()
        list.add(10)
        list.add(20)
        list.add(30)
        list.add(40)
        list.add(50)
        list.add(100)
        list.add(200)
        list.add(300)
        list.add(1000)
        internetAdapter= InternetAdapter(list)
        binding.apply {
            recView.adapter=internetAdapter
        }
    }

}