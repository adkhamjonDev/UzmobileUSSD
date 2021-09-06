package uz.adkhamjon.uzmobileussd.fragments.xizmat

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.SmsAdapter
import uz.adkhamjon.uzmobileussd.adapters.XizmatAdapter
import uz.adkhamjon.uzmobileussd.databinding.DefaultItemBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentSmsBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentXizmatBinding
import uz.adkhamjon.uzmobileussd.databinding.MyDialogBinding
import uz.adkhamjon.uzmobileussd.fragments.news.NewsModel
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

class XizmatFragment : Fragment(R.layout.fragment_xizmat) {
    private val binding by viewBinding(FragmentXizmatBinding::bind)
    private lateinit var xizmatAdapter: XizmatAdapter
    private lateinit var list:ArrayList<XizmatModel>
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)
        list= ArrayList()


        reference.child("xizmat").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val xizmatModel=snapshot.getValue(XizmatModel::class.java)
                if (xizmatModel != null) {
                    list.add(xizmatModel)
                }
                xizmatAdapter.notifyDataSetChanged()
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




        xizmatAdapter= XizmatAdapter(list,object:XizmatAdapter.OnItemClickListener{
            override fun onItemXizmat(xizmatModel: XizmatModel) {
                val builder = AlertDialog.Builder(context)
                val binding1 = MyDialogBinding.inflate(layoutInflater, null, false)
                builder.setView(binding1.root)
                binding1.name.text=xizmatModel.name
                binding1.description.text=xizmatModel.description
                builder.setPositiveButton(R.string.batafsil,
                    DialogInterface.OnClickListener { dialog, id ->
                        val str=SharedPreference.getInstance(requireContext()).lang
                        val url = xizmatModel.urlLink
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(url)
                        startActivity(i)

                    })
                builder.setNegativeButton(R.string.back,
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                val alertDialog = builder.create()
                alertDialog.show()
            }
        })
        binding.apply {


            binding.recView.adapter=xizmatAdapter
        }
    }
}