package uz.adkhamjon.uzmobileussd.utils

import android.content.Context
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.adkhamjon.uzmobileussd.R
import uz.adkhamjon.uzmobileussd.UzmobileActivity

object Config {
    const val BALANCE="*105#"
    const val UZMOBILE="Uzmobile"

    fun run(context: Context,str:String){
        val tManager = context
            .getSystemService(AppCompatActivity.TELEPHONY_SERVICE) as TelephonyManager
        val carrierName = tManager.networkOperatorName
        if(carrierName=="Uzmobile"){
            RunUssd.call(
                context,
                str,
            )
        }else{
            Toast.makeText(context, R.string.error_msg, Toast.LENGTH_SHORT).show()
        }
    }

}