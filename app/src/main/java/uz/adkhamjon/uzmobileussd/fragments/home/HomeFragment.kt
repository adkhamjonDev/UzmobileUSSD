package uz.adkhamjon.uzmobileussd.fragments.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.*
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.BannerPagerAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentHomeBinding
import uz.adkhamjon.uzmobileussd.fragments.news.NewsModel
import uz.adkhamjon.uzmobileussd.utils.SharedPreference


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var bannerPagerAdapter: BannerPagerAdapter
    private lateinit var list: ArrayList<String>
    private var position=0
    private lateinit var handler: Handler
    private lateinit var timeCounter:Runnable
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("bannerImages")
        handler=Handler(Looper.getMainLooper())
        list= ArrayList()
        reference.addChildEventListener(object : ChildEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val image=snapshot.getValue(String::class.java)
                if (image != null) {
                    list.add(image)
                }
                bannerPagerAdapter.notifyDataSetChanged()
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
        bannerPagerAdapter= BannerPagerAdapter(requireContext(),list)
        binding.apply {
            banner.adapter=bannerPagerAdapter
            indicatorView.count=list.size


            timeCounter = object : Runnable {
                override fun run() {
                    if (position + 1 > list.size) {
                        position = 0
                    } else {
                        position++
                    }
                    banner.currentItem = position
                    handler.postDelayed(this, 3000)
                }
            }
            handler.postDelayed(timeCounter, 3000)
            banner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrolled(
                    pos: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    position=pos
                }
                override fun onPageSelected(position: Int) {
                    indicatorView.selection=position
                }
                override fun onPageScrollStateChanged(state: Int) {
                }
            })
            //------------------------------------------------------------------------
            ussd.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_USSDFragment)
            }
            tarif.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_tarifFragment)
            }
            xizmat.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_xizmatFragment)
            }
            minute.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_minuteFragment)
            }
            internet.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_internetFragment)
            }
            sms.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_smsFragment)
            }
            logo.setOnClickListener {
                val url = "https://uztelecom.uz/${SharedPreference.getInstance(requireContext()).lang.subSequence(0,2)}"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(timeCounter)
    }

}