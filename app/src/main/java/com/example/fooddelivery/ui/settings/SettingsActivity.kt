package com.example.fooddelivery.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.PreferenceManager
import com.example.fooddelivery.ui.login.LoginActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = PreferenceManager(this)

        findViewById<LinearLayout>(R.id.languageRow).setOnClickListener {
            startActivity(Intent(this, LanguageSettingsActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.themeRow).setOnClickListener {
            startActivity(Intent(this, ThemeSettingsActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.privacyRow).setOnClickListener {
            startActivity(Intent(this, PrivacyPolicyActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.termsRow).setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.aboutRow).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.logoutRow).setOnClickListener {
            prefs.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
