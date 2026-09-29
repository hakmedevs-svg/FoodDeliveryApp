package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.fooddelivery.R
import com.example.fooddelivery.data.local.PreferenceManager

class ThemeSettingsActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_settings)

        preferenceManager = PreferenceManager(this)

        val radioGroup = findViewById<RadioGroup>(R.id.themeRadioGroup)
        val currentTheme = preferenceManager.getTheme()

        val themes = mapOf(
            "light" to "Light",
            "dark" to "Dark",
            "auto" to "Auto (System)"
        )

        themes.forEach { (themeCode, themeName) ->
            val radioButton = RadioButton(this).apply {
                id = themes.keys.toList().indexOf(themeCode)
                text = themeName
                isChecked = themeCode == currentTheme
                textSize = 16f
                setPadding(16, 16, 16, 16)
            }
            radioGroup.addView(radioButton)
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val themeCode = themes.keys.toList()[checkedId]
            preferenceManager.setTheme(themeCode)
            applyTheme(themeCode)
        }
    }

    private fun applyTheme(theme: String) {
        when (theme) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
