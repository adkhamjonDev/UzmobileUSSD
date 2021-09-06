package uz.adkhamjon.uzmobileussd.utils
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import uz.adkhamjon.uzmobileussd.R

class NetworkChangeListener : BroadcastReceiver() {
    private lateinit var internetConnectionHelper:InternetConnectionHelper
    override fun onReceive(context: Context?, intent: Intent?) {
        internetConnectionHelper= InternetConnectionHelper(context!!)
        if(!internetConnectionHelper.isNetworkConnected()){
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle(R.string.internet)
            dialog.setMessage(R.string.error_msg_2)
            dialog.setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id ->
                    onReceive(context, intent)
                })
            val alertDialog = dialog.create()
            alertDialog.show()
        }
    }

}