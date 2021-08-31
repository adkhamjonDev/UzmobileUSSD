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
            startActivity(Intent(this@SettingsActivity, UzmobileActivity::class.java))
            finish()
        }
        binding.russian.setOnClickListener {
            SharedPreference.getInstance(this).lang = "russian"
            startActivity(Intent(this@SettingsActivity, UzmobileActivity::class.java))
            finish()

        }
        binding.latin.setOnClickListener {
            SharedPreference.getInstance(this).lang = "latin"
            startActivity(Intent(this@SettingsActivity, UzmobileActivity::class.java))
            finish()
        }
    }
}