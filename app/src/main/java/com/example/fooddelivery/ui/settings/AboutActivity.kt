package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        findViewById<TextView>(R.id.aboutContent).text =
            "FoodDelivery is a modern local delivery app designed for Iraqi cities. It supports nearby restaurant discovery, governorate-based selection, language choices, secure account handling, and settings management."
    }
}
