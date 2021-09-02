package uz.adkhamjon.uzmobileussd.fragments.operator

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.OperatorRvAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentOperatorBinding
import uz.adkhamjon.uzmobileussd.databinding.MyDialogBinding
import uz.adkhamjon.uzmobileussd.fragments.ussd.UssdModel
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import android.Manifest


class OperatorFragment : Fragment(R.layout.fragment_operator) {
    private val binding by viewBinding(FragmentOperatorBinding::bind)
    private lateinit var list:ArrayList<OperatorModel>
    private lateinit var operatorRvAdapter: OperatorRvAdapter
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)


        list=ArrayList()

        reference.child("operator").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val operatorModel=snapshot.getValue(OperatorModel::class.java)
                if (operatorModel != null) {
                    list.add(operatorModel)
                }
                operatorRvAdapter.notifyDataSetChanged()
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



        operatorRvAdapter= OperatorRvAdapter(list,object:OperatorRvAdapter.OnItemClickListener{
            override fun onItemClick(operatorModel: OperatorModel) {
                val builder = AlertDialog.Builder(context)
                val binding1 = MyDialogBinding.inflate(layoutInflater, null, false)
                builder.setView(binding1.root)
                binding1.name.text=operatorModel.number
                binding1.description.text=operatorModel.description
                builder.setPositiveButton(R.string.call,
                    DialogInterface.OnClickListener { dialog, id ->
                        val callIntent = Intent(Intent.ACTION_CALL)
                        callIntent.data = Uri.parse("tel:${operatorModel.number}")
                        if (ContextCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.CALL_PHONE
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            startActivity(callIntent)
                        } else {
                            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1);
                        }

                    })
                builder.setNegativeButton(R.string.back,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                val alertDialog = builder.create()
                alertDialog.show()
            }
        })
        binding.apply {


            recView.adapter=operatorRvAdapter
        }
    }
}