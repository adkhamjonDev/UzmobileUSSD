package uz.adkhamjon.uzmobileussd.fragments.internet
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.InternetAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentInternetBinding
import uz.adkhamjon.uzmobileussd.fragments.sms.SmsModel
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

private const val ARG_PARAM1 = "param1"
class InternetPagerFragment : Fragment() {
    private var param1: String? = null
    private lateinit var binding:FragmentInternetBinding
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
        binding=FragmentInternetBinding.inflate(layoutInflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)



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


            }
        })
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