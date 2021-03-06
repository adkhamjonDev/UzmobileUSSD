package uz.adkhamjon.uzmobileussd.fragments.internet
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
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetPagerBinding
import uz.adkhamjon.uzmobileussd.databinding.MyDialog2Binding
import uz.adkhamjon.uzmobileussd.fragments.sms.SmsModel
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
class InternetPagerFragment : Fragment() {
    private var param1: String? = null
    private lateinit var binding:FragmentInternetPagerBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var internetAdapter: InternetAdapter
    private lateinit var list:ArrayList<InternetModel>
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
        binding=FragmentInternetPagerBinding.inflate(layoutInflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)


        list=ArrayList()
        reference.child("internet/internet").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val internetModel=snapshot.getValue(InternetModel::class.java)
                if (internetModel != null) {
                    if(internetModel.type==param1){
                        list.add(internetModel)
                    }
                }
                internetAdapter.notifyDataSetChanged()
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

        internetAdapter= InternetAdapter(list,object :InternetAdapter.OnItemClickListener{
            override fun onItemInternet(internetModel: InternetModel) {

                val builder = AlertDialog.Builder(context)
                val binding1 = MyDialog2Binding.inflate(layoutInflater,null,false)
                builder.setView(binding1.root)
                binding1.name.text="${internetModel.name}"
                binding1.cost.text=internetModel.cost
                binding1.number.text=internetModel.number
                binding1.deadline.text=internetModel.deadline
                binding1.faollashtirish.text=internetModel.activation
                if(internetModel.disable=="null"){
                    binding1.uchirish.visibility=View.GONE
                }else{
                    binding1.uchirish.text=internetModel.disable
                }
                binding1.share.setOnClickListener {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "http://play.google.com/store/apps/details?id=uz.pdp.uzmobile\n\n${internetModel.name}: ${internetModel.activation}"
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                }
                binding1.run.setOnClickListener {
                    Config.run(requireContext(),internetModel.activation!!)
                }
                builder.setNegativeButton(R.string.back,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                val alertDialog = builder.create()
                alertDialog.show()



            }
        })
        binding.recView.adapter=internetAdapter
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            InternetPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}