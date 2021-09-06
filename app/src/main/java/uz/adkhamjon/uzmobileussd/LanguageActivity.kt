package uz.adkhamjon.uzmobileussd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yariksoffice.lingver.Lingver
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
            SharedPreference.getInstance(this).lang = "uzbek"
            SharedPreference.getInstance(this).setHasLang(true)
            setNewLocale(App.LANGUAGE_ENGLISH, App.LANGUAGE_ENGLISH_COUNTRY)


        }
        binding.russian.setOnClickListener {
            SharedPreference.getInstance(this).lang = "russian"
            SharedPreference.getInstance(this).setHasLang(true)
            setNewLocale(App.LANGUAGE_RUSSIAN, App.LANGUAGE_RUSSIAN_COUNTRY)

        }
        binding.latin.setOnClickListener {
            SharedPreference.getInstance(this).lang = "latin"
            SharedPreference.getInstance(this).setHasLang(true)
            setNewLocale(App.LANGUAGE_UZBEK, App.LANGUAGE_UZBEK_COUNTRY)

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