package com.example.fooddelivery.ui.settings

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.fooddelivery.R
import com.example.fooddelivery.data.PreferenceManager

class ThemeSettingsActivity : AppCompatActivity() {

    private lateinit var prefs: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_settings)
        prefs = PreferenceManager(this)

        val group = findViewById<RadioGroup>(R.id.themeGroup)
        val themes = listOf(
            "light" to "Light",
            "dark" to "Dark",
            "auto" to "System"
        )

        themes.forEachIndexed { index, entry ->
            val button = RadioButton(this).apply {
                id = index
                text = entry.second
                isChecked = prefs.getTheme() == entry.first
            }
            group.addView(button)
        }

        group.setOnCheckedChangeListener { _, checkedId ->
            val selected = themes[checkedId].first
            prefs.saveTheme(selected)
            when (selected) {
                "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}
