package uz.adkhamjon.uzmobileussd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.adkhamjon.uzmobileussd.databinding.ActivityLanguageBinding
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import java.util.*

class LanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uzbek.setOnClickListener {
            SharedPreference.getInstance(this).lang = "en"
            SharedPreference.getInstance(this).setHasLang(true)
            val intent = Intent(this, UzmobileActivity::class.java)
            startActivity(intent)
            finish()


        }
        binding.russian.setOnClickListener {
            SharedPreference.getInstance(this).lang = "ru"
            SharedPreference.getInstance(this).setHasLang(true)
            val intent = Intent(this, UzmobileActivity::class.java)
            startActivity(intent)
            finish()


        }
        binding.latin.setOnClickListener {
            SharedPreference.getInstance(this).lang = "uz"
            SharedPreference.getInstance(this).setHasLang(true)
            val intent = Intent(this, UzmobileActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
    private fun setLanguage() {
        val locale = Locale(SharedPreference.getInstance(this).lang)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onDestroy() {
        super.onDestroy()
        setLanguage()
    }
}