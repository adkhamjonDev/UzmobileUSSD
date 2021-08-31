package uz.adkhamjon.uzmobileussd.fragments.ussd

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.UssdAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentUSSDBinding
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.RunUssd
import uz.adkhamjon.uzmobileussd.utils.SharedPreference


class USSDFragment : Fragment(R.layout.fragment_u_s_s_d) {
    private val binding by viewBinding(FragmentUSSDBinding::bind)
    private lateinit var ussdAdapter: UssdAdapter
    private lateinit var list:ArrayList<UssdModel>
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private val TAG = "USSDFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)
        list= ArrayList()


        reference.child("ussd").addChildEventListener(object :ChildEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val ussdModel=snapshot.getValue(UssdModel::class.java)
                if (ussdModel != null) {
                    list.add(ussdModel)
                }
                ussdAdapter.notifyDataSetChanged()
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

        ussdAdapter=UssdAdapter(list, object :UssdAdapter.OnItemClickListener{
            override fun onItemRun(str: String) {
                Config.run(requireContext(),str)
            }
        })
        binding.apply {
            recView.adapter=ussdAdapter
        }
    }
}