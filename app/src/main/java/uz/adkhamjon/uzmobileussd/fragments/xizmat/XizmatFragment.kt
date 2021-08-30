package uz.adkhamjon.uzmobileussd.fragments.xizmat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.adapters.SmsAdapter
import uz.adkhamjon.uzmobileussd.adapters.XizmatAdapter
import uz.adkhamjon.uzmobileussd.databinding.FragmentSmsBinding
import uz.adkhamjon.uzmobileussd.databinding.FragmentXizmatBinding

class XizmatFragment : Fragment(R.layout.fragment_xizmat) {
    private val binding by viewBinding(FragmentXizmatBinding::bind)
    private lateinit var xizmatAdapter: XizmatAdapter
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
        xizmatAdapter= XizmatAdapter(list)
        binding.apply {
            recView.adapter=xizmatAdapter
        }
    }
}