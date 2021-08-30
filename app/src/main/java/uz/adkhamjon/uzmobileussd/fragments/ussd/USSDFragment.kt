package uz.adkhamjon.uzmobileussd.fragments.ussd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.TarifAdapter
import uz.adkhamjon.uzmobileussd.adapters.UssdAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentUSSDBinding

class USSDFragment : Fragment(R.layout.fragment_u_s_s_d) {
    private val binding by viewBinding(FragmentUSSDBinding::bind)
    private lateinit var ussdAdapter: UssdAdapter
    private lateinit var list:ArrayList<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list= ArrayList()
        list.add("Street")
        list.add("Onlime")
        list.add("Flash")
        list.add("Royal")
        list.add("Ishblarmon")
        list.add("Oddiy 10")
        list.add("Oddiy 10")
        list.add("Oddiy 10")
        list.add("Oddiy 10")
        list.add("Oddiy 10")
        ussdAdapter= UssdAdapter(list)
        binding.apply {

            recView.adapter=ussdAdapter

        }
    }
}