package com.example.fooddelivery.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.IraqiGovernorates
import com.example.fooddelivery.data.PreferenceManager
import com.example.fooddelivery.ui.main.MainActivity

class GovernorateSelectionActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var continueButton: Button
    private lateinit var prefs: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_governorate)

        prefs = PreferenceManager(this)
        spinner = findViewById(R.id.governorateSpinner)
        continueButton = findViewById(R.id.continueButton)

        val names = IraqiGovernorates.list.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, names)
        spinner.adapter = adapter

        continueButton.setOnClickListener {
            val selected = spinner.selectedItem.toString()
            prefs.saveGovernorate(selected)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
