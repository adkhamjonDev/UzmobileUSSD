package uz.adkhamjon.uzmobileussd

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import uz.adkhamjon.uzmobileussd.databinding.ActivityUzmobileBinding
import uz.adkhamjon.uzmobileussd.utils.Config
import uz.adkhamjon.uzmobileussd.utils.SharedPreference
import java.util.*
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork





class UzmobileActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUzmobileBinding
    private lateinit var navController: NavController
    private var backPressedToExitOnce = false
    private var l=""
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUzmobileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    setLanguage()
                    setSupportActionBar(binding.appBarUzmobile.toolbar)
                    val drawerLayout: DrawerLayout = binding.drawerLayout
                    val navView: NavigationView = binding.navView
                    navController = findNavController(R.id.nav_host_fragment_content_uzmobile)
                    appBarConfiguration = AppBarConfiguration(
                        setOf(
                            R.id.homeFragment
                        ), drawerLayout
                    )
                    setupActionBarWithNavController(navController, appBarConfiguration)
                    navView.setupWithNavController(navController)
                    binding.navView.setNavigationItemSelectedListener {
                        when (it.itemId) {
                            R.id.telegram -> {
                                val uri =
                                    Uri.parse("https://t.me/ussduz")
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                try {
                                    startActivity(intent)
                                } catch (e: Exception) {
                                    binding.drawerLayout.close()
                                }
                                binding.drawerLayout.close()
                            }
                            R.id.share -> {
                                ShareCompat.IntentBuilder.from(this@UzmobileActivity)
                                    .setType("text/plain")
                                    .setText("http://play.google.com/store/apps/details?id=uz.pdp.uzmobile")
                                    .startChooser()

                            }
                            R.id.rate -> {
                                val uri = Uri.parse("market://details?id=" + this@UzmobileActivity.packageName)
                                val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                                try {
                                    startActivity(goToMarket)
                                } catch (e: ActivityNotFoundException) {
                                    startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("http://play.google.com/store/apps/details?id=uz.pdp.uzmobile")
                                        )
                                    )
                                }
                            }
                            R.id.call -> {
                                val dialog = AlertDialog.Builder(this@UzmobileActivity)
                                dialog.setTitle(R.string.biz_blan_aloqa)
                                dialog.setMessage("Email: ussdmobile@gamil.com")
                                dialog.setPositiveButton(R.string.send_email,
                                    DialogInterface.OnClickListener { dialog, id ->
                                        val intent = Intent(Intent.ACTION_SENDTO)
                                        intent.data =
                                            Uri.parse("mailto:") // only email apps should handle this

                                        intent.putExtra(Intent.EXTRA_EMAIL, "ussdmobile@gamil.com")
                                        intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
                                        if (intent.resolveActivity(packageManager) != null) startActivity(intent)


                                    })
                                dialog.setNegativeButton(R.string.back,
                                    DialogInterface.OnClickListener { dialog, id ->

                                    })
                                val alertDialog = dialog.create()
                                alertDialog.show()
                            }
                            R.id.about -> {
                                val dialog = AlertDialog.Builder(this@UzmobileActivity)
                                dialog.setTitle(R.string.about_us)
                                dialog.setMessage(R.string.about_txt)
                                dialog.setPositiveButton(R.string.back,
                                    DialogInterface.OnClickListener { dialog, id ->



                                    })
                                val alertDialog = dialog.create()
                                alertDialog.show()

                            }
                        }
                        drawerLayout.closeDrawers()
                        true
                    }
                    binding.appBarUzmobile.mainScreen.balance.setOnClickListener {
                        Config.run(this@UzmobileActivity,"*105#")
                    }
                    binding.appBarUzmobile.mainScreen.operator.setOnClickListener {

                        navController.navigate(R.id.operatorFragment)

                    }
                    binding.appBarUzmobile.mainScreen.home.setOnClickListener {

                        if (navController.currentDestination!!.id != R.id.homeFragment) {
                            navController.popBackStack()
                        }

                    }
                    binding.appBarUzmobile.mainScreen.news.setOnClickListener {

                        navController.navigate(R.id.newsFragment)

                    }
                    binding.appBarUzmobile.mainScreen.settings.setOnClickListener {

                        val intent=Intent(applicationContext,SettingsActivity::class.java)
                        startActivity(intent)

                    }

                }
                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {

                }
            }).check()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.info_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.telegram->{
                val uri =
                    Uri.parse("https://t.me/ussduz")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    binding.drawerLayout.close()
                }
                binding.drawerLayout.close()
            }
            R.id.share->{
                val sendIntent = Intent()
                sendIntent.action = Intent.ACTION_SEND
                sendIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "http://play.google.com/store/apps/details?id=uz.pdp.uzmobile"
                )
                sendIntent.type = "text/plain"
                startActivity(sendIntent)
            }
        }
         return super.onOptionsItemSelected(item)
    }
    private fun setLanguage() {
        val lang = SharedPreference.getInstance(this).lang
        if(lang=="uzbek"){
            l="en"
        }
        else if(lang=="russian"){
            l="ru"
        }
        else{
            l="uz"
        }
        val locale = Locale(l)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_uzmobile)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            val count = supportFragmentManager.backStackEntryCount
            if (count == 0) {
                if (backPressedToExitOnce) {
                    super.onBackPressed()
                } else {
                    this.backPressedToExitOnce = true
                    Toast.makeText(this, resources.getString(R.string.ask_exit), Toast.LENGTH_SHORT)
                        .show()
                    Handler().postDelayed({
                        backPressedToExitOnce = false
                    }, 2000)
                }
            } else {
                supportFragmentManager.popBackStack()
            }
        }
    }
    override fun onRestart() {
        super.onRestart()
        recreate()
        setLanguage()
    }
    fun hideProgress() {

    }

    fun showProgress() {

    }
}


