package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.PreferenceManager
import com.example.fooddelivery.data.model.AvailableLanguages

class LanguageSettingsActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_settings)

        preferenceManager = PreferenceManager(this)

        val radioGroup = findViewById<RadioGroup>(R.id.languageRadioGroup)
        val currentLanguage = preferenceManager.getLanguage()

        // Create radio buttons for each language
        AvailableLanguages.languages.forEachIndexed { index, language ->
            val radioButton = RadioButton(this).apply {
                id = index
                text = "${language.flag} ${language.nativeName}"
                isChecked = language.code == currentLanguage
                textSize = 16f
                setPadding(16, 16, 16, 16)
            }
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId >= 0 && checkedId < AvailableLanguages.languages.size) {
                val selectedLanguage = AvailableLanguages.languages[checkedId]
                preferenceManager.setLanguage(selectedLanguage.code)
                // In production, recreate activity or restart app to apply language
            }
        }
    }
}
