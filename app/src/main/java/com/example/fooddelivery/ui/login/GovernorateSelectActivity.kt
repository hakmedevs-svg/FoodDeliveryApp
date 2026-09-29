package com.example.fooddelivery.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.PreferenceManager
import com.example.fooddelivery.data.model.IraqiGovernorates
import com.example.fooddelivery.ui.main.MainActivity

class GovernorateSelectActivity : AppCompatActivity() {

    private lateinit var governorateSpinner: Spinner
    private lateinit var continueButton: Button
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_governorate_select)

        preferenceManager = PreferenceManager(this)

        governorateSpinner = findViewById(R.id.governorateSpinner)
        continueButton = findViewById(R.id.continueButton)

        // Setup spinner with Iraqi governorates
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            IraqiGovernorates.governorates
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        governorateSpinner.adapter = adapter

        continueButton.setOnClickListener {
            val selectedGovernorate = governorateSpinner.selectedItem.toString()
            preferenceManager.setSelectedGovernorate(selectedGovernorate)
            Toast.makeText(
                this,
                "تم اختيار: $selectedGovernorate",
                Toast.LENGTH_SHORT
            ).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
