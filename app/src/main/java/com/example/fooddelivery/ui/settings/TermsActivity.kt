package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R

class TermsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)
        findViewById<TextView>(R.id.termsContent).text =
            "By using this app, you agree to use the service in a lawful and respectful manner. We do not guarantee delivery times, and order availability may vary based on region. Users must keep account information secure and must not misuse the platform or attempt to manipulate offers or promos."
    }
}
