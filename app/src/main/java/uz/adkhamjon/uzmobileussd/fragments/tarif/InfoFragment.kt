package uz.adkhamjon.uzmobileussd.fragments.tarif

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.UzmobileActivity
import uz.adkhamjon.uzmobileussd.databinding.FragmentInfoBinding
import uz.adkhamjon.uzmobileussd.utils.Config


class InfoFragment : Fragment(R.layout.fragment_info) {
  private val binding by viewBinding(FragmentInfoBinding::bind)
  private lateinit  var tarifModel:TarifModel


  @SuppressLint("SetTextI18n")
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if(arguments!=null){
      tarifModel=arguments?.get("object")as TarifModel
    }
    (activity as UzmobileActivity?)?.setTittle("\"${tarifModel.name}\"")
    binding.apply {

      more.setOnClickListener {
        val url = tarifModel.information
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
      }
      run.setOnClickListener {
        tarifModel.code?.let { it1 -> Config.run(requireContext(), it1) }
      }

      name.text="\"${tarifModel.name}\""
      name2.text=tarifModel.name
      payment.text=tarifModel.payment
      bannerInternet.text=tarifModel.internetLimit
      bannerSms.text=tarifModel.smsLimit
      bannerMinute.text="${tarifModel.minuteLimitMonth} M"

      payment2.text="${requireContext().getString(R.string.payment)} ${tarifModel.payment} ${requireContext().getString(R.string.so_m)}"
      minute.text="${requireContext().getString(R.string.uzb)} ${tarifModel.minuteLimitMonth} ${requireContext().getString(R.string.daqiqa)}"
      minuteNetwork.text="${requireContext().getString(R.string.network)} ${tarifModel.minuteLimitNetwork} ${requireContext().getString(R.string.daqiqa)}"
      internet.text="${requireContext().getString(R.string.mob_internet)} ${tarifModel.internetLimit}"
      sms.text="${requireContext().getString(R.string.sms_uzb)} ${tarifModel.smsLimit}"


      change.text="${requireActivity().getText(R.string.change)} ${tarifModel.code}"

    }
  }
}