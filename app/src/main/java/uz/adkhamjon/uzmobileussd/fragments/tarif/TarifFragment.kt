package uz.adkhamjon.uzmobileussd.fragments.tarif

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.adapters.TarifAdapter
import uz.adkhamjon.uzmobileussd.adapters.UssdAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentTarifBinding
import uz.adkhamjon.uzmobileussd.fragments.ussd.UssdModel

class TarifFragment : Fragment(R.layout.fragment_tarif) {
    private val binding by viewBinding(FragmentTarifBinding::bind)
    private lateinit var tarifAdapter: TarifAdapter
    private lateinit var list:ArrayList<TarifModel>
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        firebaseDatabase= FirebaseDatabase.getInstance()
//        reference=firebaseDatabase.getReference("latin")

        list= ArrayList()

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