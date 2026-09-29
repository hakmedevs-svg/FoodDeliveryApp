package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelivery.R
import com.example.fooddelivery.data.PreferenceManager

class LanguageSettingsActivity : AppCompatActivity() {

    private lateinit var prefs: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_settings)
        prefs = PreferenceManager(this)

        val group = findViewById<RadioGroup>(R.id.languageGroup)
        val languages = listOf(
            "ar" to "العربية",
            "en" to "English",
            "fr" to "Français",
            "es" to "Español",
            "de" to "Deutsch",
            "it" to "Italiano",
            "pt" to "Português",
            "ru" to "Русский",
            "ja" to "日本語",
            "ko" to "한국어",
            "zh" to "中文",
            "tr" to "Türkçe",
            "ur" to "اردو"
        )

        languages.forEachIndexed { index, (code, label) ->
            val button = RadioButton(this).apply {
                id = index
                text = label
                isChecked = prefs.getLanguage() == code
            }
            group.addView(button)
        }

        group.setOnCheckedChangeListener { _, checkedId ->
            val selected = languages[checkedId].first
            prefs.saveLanguage(selected)
        }
    }
}
