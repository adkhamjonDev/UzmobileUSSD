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
            RunUssd.call(
                context,
                str,
            )
    }
}