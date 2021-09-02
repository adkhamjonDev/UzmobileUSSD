package uz.adkhamjon.uzmobileussd.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import uz.adkhamjon.uzmobileussd.utils.InternetConnectionHelper

class NetworkChangeListener : BroadcastReceiver() {
    private lateinit var internetConnectionHelper: InternetConnectionHelper
    override fun onReceive(context: Context?, intent: Intent?,) {
        internetConnectionHelper= InternetConnectionHelper(context!!)
        if(!internetConnectionHelper.isNetworkConnected()){


        }
    }

}