package uz.adkhamjon.uzmobileussd.fragments.tarif

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.adapters.TarifAdapter
import uz.adkhamjon.uzmobileussd.adapters.UssdAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentTarifBinding
import uz.adkhamjon.uzmobileussd.fragments.operator.OperatorModel
import uz.adkhamjon.uzmobileussd.fragments.ussd.UssdModel
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

class TarifFragment : Fragment(R.layout.fragment_tarif) {
    private val binding by viewBinding(FragmentTarifBinding::bind)
    private lateinit var tarifAdapter: TarifAdapter
    private lateinit var list:ArrayList<TarifModel>
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)
        list=ArrayList()
        reference.child("tariflar").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val tarifModel=snapshot.getValue(TarifModel::class.java)
                if (tarifModel != null) {
                    list.add(tarifModel)
                }
                tarifAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {


            }

            override fun onChildRemoved(snapshot: DataSnapshot) {


            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {


            }

            override fun onCancelled(error: DatabaseError) {


            }

        })

        tarifAdapter= TarifAdapter(requireContext(),list,object :TarifAdapter.OnItemClickListener{
            override fun onItemTarif(tarifModel: TarifModel) {

                findNavController().navigate(R.id.action_tarifFragment_to_infoFragment, bundleOf("object" to tarifModel))

            }
        })
        binding.apply {

            recView.adapter=tarifAdapter
            checkTarif.setOnClickListener {
                Config.run(requireContext(),"*107#")
            }

        }
    }
}