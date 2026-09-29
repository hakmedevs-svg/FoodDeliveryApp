package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R

class PrivacyPolicyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        findViewById<TextView>(R.id.privacyContent).text =
            "We collect only the minimum necessary information to provide food delivery services, such as device info, order location, and phone number. Data is protected using encrypted storage and is never shared without consent. You can request access, deletion, or update of your personal data at any time."
    }
}
