package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.PreferenceManager

class NotificationsSettingsActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications_settings)

        preferenceManager = PreferenceManager(this)

        val notificationsSwitch = findViewById<Switch>(R.id.notificationsSwitch)
        notificationsSwitch.isChecked = preferenceManager.isNotificationsEnabled()

        notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferenceManager.setNotificationsEnabled(isChecked)
            Toast.makeText(
                this,
                if (isChecked) "تم تفعيل الإشعارات" else "تم تعطيل الإشعارات",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
