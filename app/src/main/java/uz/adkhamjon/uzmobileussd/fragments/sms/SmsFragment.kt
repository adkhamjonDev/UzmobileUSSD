package uz.adkhamjon.uzmobileussd.fragments.sms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.MinuteAdapter
import uz.adkhamjon.uzmobileussd.adapters.SmsAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentMinuteBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentSmsBinding

class SmsFragment : Fragment(R.layout.fragment_sms) {
    private val binding by viewBinding(FragmentSmsBinding::bind)
    private lateinit var smsAdapter: SmsAdapter
    private lateinit var list:ArrayList<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        list= ArrayList()
        list.add("10")
        list.add("20")
        list.add("30")
        list.add("40")
        list.add("50")
        list.add("100")
        list.add("200")
        list.add("300")
        list.add("1000")
        smsAdapter= SmsAdapter(list)
        binding.apply {
            recView.adapter=smsAdapter
        }
    }
}