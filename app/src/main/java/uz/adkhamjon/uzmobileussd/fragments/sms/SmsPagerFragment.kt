package uz.adkhamjon.uzmobileussd.fragments.sms

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.*
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.SmsAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentSmsPagerBinding
import uz.adkhamjon.uzmobileussd.databinding.MyDialog2Binding
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

private const val ARG_PARAM1 = "param1"

class SmsPagerFragment : Fragment() {
    private var param1: String? = null
    private lateinit var binding:FragmentSmsPagerBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var smsAdapter: SmsAdapter
    private lateinit var list: ArrayList<SmsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
         binding=FragmentSmsPagerBinding.inflate(inflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)
        list= ArrayList()
        reference.child("sms/$param1").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val smsModel=snapshot.getValue(SmsModel::class.java)
                if (smsModel != null) {
                    list.add(smsModel)
                }
                smsAdapter.notifyDataSetChanged()
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
        smsAdapter= SmsAdapter(requireContext(),list,object:SmsAdapter.OnItemClickListener{
            override fun onItemSms(smsModel: SmsModel) {
                val builder = AlertDialog.Builder(context)
                val binding1 = MyDialog2Binding.inflate(layoutInflater,null,false)
                builder.setView(binding1.root)
                binding1.name.text="${smsModel.name} SMS"
                binding1.cost.text=smsModel.cost
                binding1.number.text=smsModel.number
                binding1.deadline.text=smsModel.deadline
                binding1.faollashtirish.text=smsModel.enable
                if(smsModel.disable=="null"){
                    binding1.uchirish.visibility=View.GONE
                }else{
                    binding1.uchirish.text=smsModel.disable
                }
                binding1.share.setOnClickListener {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "http://play.google.com/store/apps/details?id=uz.pdp.uzmobile\n\n${smsModel.name}: ${smsModel.enable}"
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                }
                binding1.run.setOnClickListener {
                    Config.run(requireContext(),smsModel.enable!!)
                }
                builder.setNegativeButton(R.string.back,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                val alertDialog = builder.create()
                alertDialog.show()
            }
        })
        binding.recView.adapter=smsAdapter

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            SmsPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}