package com.example.fooddelivery.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.PreferenceManager
import com.example.fooddelivery.ui.login.LoginActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        preferenceManager = PreferenceManager(this)

        // Language settings
        findViewById<LinearLayout>(R.id.languageOption).setOnClickListener {
            startActivity(Intent(this, LanguageSettingsActivity::class.java))
        }

        // Theme settings
        findViewById<LinearLayout>(R.id.themeOption).setOnClickListener {
            startActivity(Intent(this, ThemeSettingsActivity::class.java))
        }

        // Notifications
        findViewById<LinearLayout>(R.id.notificationsOption).setOnClickListener {
            startActivity(Intent(this, NotificationsSettingsActivity::class.java))
        }

        // Privacy Policy
        findViewById<LinearLayout>(R.id.privacyOption).setOnClickListener {
            startActivity(Intent(this, PrivacyPolicyActivity::class.java))
        }

        // Terms of Use
        findViewById<LinearLayout>(R.id.termsOption).setOnClickListener {
            startActivity(Intent(this, TermsOfUseActivity::class.java))
        }

        // About
        findViewById<LinearLayout>(R.id.aboutOption).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        // Logout
        findViewById<LinearLayout>(R.id.logoutOption).setOnClickListener {
            preferenceManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Update phone display
        val phoneDisplay = findViewById<TextView>(R.id.phoneDisplay)
        val phone = preferenceManager.getPhoneNumber()
        if (phone.isNotEmpty()) {
            phoneDisplay.text = phone.replace(Regex("(?<=\\d{4})\\d(?=\\d{4})"), "*")
        }
    }
}
