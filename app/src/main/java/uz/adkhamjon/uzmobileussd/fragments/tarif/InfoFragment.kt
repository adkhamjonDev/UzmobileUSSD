package uz.adkhamjon.uzmobileussd.fragments.tarif

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.databinding.FragmentInfoBinding


class InfoFragment : Fragment(R.layout.fragment_info) {
  private val binding by viewBinding(FragmentInfoBinding::bind)


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.apply {




    }
  }
}