package uz.adkhamjon.uzmobileussd.fragments.tarif

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.adapters.TarifAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentTarifBinding

class TarifFragment : Fragment(R.layout.fragment_tarif) {
    private val binding by viewBinding(FragmentTarifBinding::bind)
    private lateinit var tarifAdapter: TarifAdapter
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
        tarifAdapter= TarifAdapter(list,object :TarifAdapter.OnItemClickListener{
            override fun onItemTarif(position: Int) {

                findNavController().navigate(R.id.action_tarifFragment_to_infoFragment)

            }
        })
        binding.apply {

            recView.adapter=tarifAdapter

        }
    }
}