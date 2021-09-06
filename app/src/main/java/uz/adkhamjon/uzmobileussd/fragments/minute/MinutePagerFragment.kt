package uz.adkhamjon.uzmobileussd.fragments.minute

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.MinuteAdapter
import uz.adkhamjon.uzmobileussd.databinding.DefaultItemBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentMinutePagerBinding
import uz.adkhamjon.uzmobileussd.databinding.MyDialog2Binding
import uz.adkhamjon.uzmobileussd.databinding.MyDialog3Binding
import uz.adkhamjon.uzmobileussd.fragments.internet.InternetModel
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference


private const val ARG_PARAM1 = "param1"


class MinutePagerFragment : Fragment() {
    private var param1: String? = null
    private lateinit var binding:FragmentMinutePagerBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list:ArrayList<MinuteModel>
    private lateinit var minuteAdapter: MinuteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentMinutePagerBinding.inflate(layoutInflater, container, false)
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)


        list=ArrayList()
        reference.child("minute/minute").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val minuteModel=snapshot.getValue(MinuteModel::class.java)
                if (minuteModel != null) {
                    if(minuteModel.type==param1){
                        list.add(minuteModel)
                    }
                }
                minuteAdapter.notifyDataSetChanged()
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
        minuteAdapter= MinuteAdapter(list,object:MinuteAdapter.OnItemClickListener{
            override fun onItemMinute(minuteModel: MinuteModel) {
                val builder = AlertDialog.Builder(context)
                val binding1 = MyDialog3Binding.inflate(layoutInflater,null,false)
                builder.setView(binding1.root)
                binding1.name.text="${minuteModel.name2}"
                binding1.berish.text=minuteModel.takdimEtish
                binding1.olish.text=minuteModel.yechibOlsih
                binding1.deadline.text=minuteModel.deadline
                binding1.number.text=minuteModel.number
                binding1.active.text=minuteModel.activation
                binding1.cost.text=minuteModel.cost
                if(minuteModel.takdimEtish=="null"){
                    binding1.berish.visibility=View.GONE
                    binding1.olish.visibility=View.GONE
                }
                if(minuteModel.number=="null"){
                        binding1.number.visibility=View.GONE
                    }


                binding1.share.setOnClickListener {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "http://play.google.com/store/apps/details?id=uz.pdp.uzmobile\n\n${minuteModel.name}: ${minuteModel.activation}"
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                }
                binding1.run.setOnClickListener {
                    Config.run(requireContext(),minuteModel.activation!!)
                }
                builder.setNegativeButton(R.string.back,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                val alertDialog = builder.create()
                alertDialog.show()


            }
        })
        binding.recView.adapter=minuteAdapter

        return  binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            MinutePagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}