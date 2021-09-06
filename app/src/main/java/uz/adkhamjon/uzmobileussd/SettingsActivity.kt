package uz.adkhamjon.uzmobileussd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yariksoffice.lingver.Lingver
import uz.adkhamjon.uzmobileussd.App.Companion.LANGUAGE_ENGLISH
import uz.adkhamjon.uzmobileussd.App.Companion.LANGUAGE_ENGLISH_COUNTRY
import uz.adkhamjon.uzmobileussd.App.Companion.LANGUAGE_RUSSIAN
import uz.adkhamjon.uzmobileussd.App.Companion.LANGUAGE_RUSSIAN_COUNTRY
import uz.adkhamjon.uzmobileussd.App.Companion.LANGUAGE_UZBEK
import uz.adkhamjon.uzmobileussd.App.Companion.LANGUAGE_UZBEK_COUNTRY
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
          setNewLocale(LANGUAGE_ENGLISH, LANGUAGE_ENGLISH_COUNTRY)

        }
        binding.russian.setOnClickListener {
            SharedPreference.getInstance(this).lang = "russian"
          setNewLocale(LANGUAGE_RUSSIAN, LANGUAGE_RUSSIAN_COUNTRY)

        }
        binding.latin.setOnClickListener {
            SharedPreference.getInstance(this).lang = "latin"
          setNewLocale(LANGUAGE_UZBEK, LANGUAGE_UZBEK_COUNTRY)

        }
    }
    private fun setNewLocale(language: String, country: String) {
        Lingver.getInstance().setLocale(this, language, country)
        restart()
    }
    private fun restart() {
        val i = Intent(this, UzmobileActivity::class.java)
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}