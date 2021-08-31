package uz.adkhamjon.uzmobileussd

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import uz.adkhamjon.uzmobileussd.fragments.ussd.UssdModel
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

class SplashActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list:ArrayList<UssdModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep((1.5 * 1000).toLong())
                    if (SharedPreference.getInstance(this@SplashActivity).haslang) {
                        startActivity(Intent(this@SplashActivity, UzmobileActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@SplashActivity, LanguageActivity::class.java))
                        finish()
                    }
                } catch (e: Exception) {
                }
            }
        }
        background.start()
    }
}