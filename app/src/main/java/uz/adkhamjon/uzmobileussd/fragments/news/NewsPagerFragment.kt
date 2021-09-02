package uz.adkhamjon.uzmobileussd.fragments.news

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import uz.adkhamjon.uzmobileussd.adapters.NewsRvAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentNewsPagerBinding
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import android.content.DialogInterface
import uz.adkhamjon.uzmobileussd.R
import android.content.Intent
import android.net.Uri
import uz.adkhamjon.uzmobileussd.databinding.MyDialogBinding


private const val ARG_PARAM1 = "param1"
class NewsPagerFragment : Fragment() {
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list:ArrayList<NewsModel>
    private var param1: String? = null
    private lateinit var binding:FragmentNewsPagerBinding
    private lateinit var newsRvAdapter: NewsRvAdapter
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
        binding=FragmentNewsPagerBinding.inflate(inflater, container, false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference(SharedPreference.getInstance(requireContext()).lang)
        list=ArrayList()
        reference.child("news/$param1").addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val newsModel=snapshot.getValue(NewsModel::class.java)
                if (newsModel != null) {
                    list.add(newsModel)
                }
                newsRvAdapter.notifyDataSetChanged()
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

        newsRvAdapter= NewsRvAdapter(list,object:NewsRvAdapter.OnItemClickListener{
            override fun onItemClick(newsModel: NewsModel) {
                val builder = AlertDialog.Builder(context)
                val binding1 = MyDialogBinding.inflate(inflater, null, false)
                builder.setView(binding1.root)
                binding1.name.text=newsModel.name
                binding1.description.text=newsModel.description
                builder.setPositiveButton(R.string.batafsil,
                    DialogInterface.OnClickListener { dialog, id ->
                        val str=SharedPreference.getInstance(requireContext()).lang
                        val url = "https://uztelecom.uz/${str.subSequence(0,2)}"
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(url)
                        startActivity(i)

                    })
                builder.setNegativeButton(R.string.back,
                    DialogInterface.OnClickListener {dialog, id ->

                })
                val alertDialog = builder.create()
                alertDialog.show()


            }
        })
        binding.recView.adapter=newsRvAdapter
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            NewsPagerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}