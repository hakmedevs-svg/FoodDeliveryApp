package com.example.fooddelivery.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.PreferenceManager
import com.example.fooddelivery.ui.main.MainActivity
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var phoneInput: EditText
    private lateinit var loginButton: Button
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferenceManager = PreferenceManager(this)

        // Check if user is already logged in
        if (preferenceManager.isUserLoggedIn()) {
            navigateToMain()
        }

        phoneInput = findViewById(R.id.phoneInput)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val phone = phoneInput.text.toString().trim()
            if (validatePhone(phone)) {
                performLogin(phone)
            } else {
                Toast.makeText(this, getString(R.string.invalid_phone), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePhone(phone: String): Boolean {
        // Iraqi phone validation (starts with 07 or +9647)
        return phone.matches(Regex("^(07|\\+9647)\\d{9}$"))
    }

    private fun performLogin(phone: String) {
        // Simulate phone authentication
        // In production, integrate with a real backend or Firebase
        loginButton.isEnabled = false
        loginButton.text = getString(R.string.logging_in)

        // Generate fake token
        val token = UUID.randomUUID().toString()

        // Save to preferences (encrypted in production)
        preferenceManager.setPhoneNumber(phone)
        preferenceManager.setAuthToken(token)

        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()

        // Navigate to governorate selection
        val intent = Intent(this, GovernorateSelectActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
