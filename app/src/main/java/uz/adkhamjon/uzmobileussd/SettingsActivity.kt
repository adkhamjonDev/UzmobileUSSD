package uz.adkhamjon.uzmobileussd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.adkhamjon.uzmobileussd.databinding.ActivitySettingsBinding
import uz.adkhamjon.uzmobileussd.utils.SharedPreference

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }
        binding.uzbek.setOnClickListener {
            SharedPreference.getInstance(this).lang = "uzbek"
            restart()

        }
        binding.russian.setOnClickListener {
            SharedPreference.getInstance(this).lang = "russian"
            restart()

        }
        binding.latin.setOnClickListener {
            SharedPreference.getInstance(this).lang = "latin"
            restart()

        }
    }
    private fun restart(){
        val intent = Intent(this, UzmobileActivity::class.java)
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        finish()
    }
}