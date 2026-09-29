package com.example.fooddelivery.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.PreferenceManager

class LoginActivity : AppCompatActivity() {

    private lateinit var phoneInput: EditText
    private lateinit var loginButton: Button
    private lateinit var prefs: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefs = PreferenceManager(this)
        if (prefs.isLoggedIn()) {
            startActivity(Intent(this, GovernorateSelectionActivity::class.java))
            finish()
            return
        }

        phoneInput = findViewById(R.id.phoneInput)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val phone = phoneInput.text.toString().trim()
            if (phone.matches(Regex("^(\\+964|964|0)7[0-9]{9}$"))) {
                prefs.savePhone(phone)
                prefs.setLoggedIn(true)
                startActivity(Intent(this, GovernorateSelectionActivity::class.java))
                finish()
            } else {
                phoneInput.error = "Enter valid Iraqi mobile number"
            }
        }
    }
}
