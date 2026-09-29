package com.example.fooddelivery.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferenceManager(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        "secure_food_delivery",
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun savePhone(phone: String) {
        prefs.edit().putString("phone", phone).apply()
    }

    fun getPhone(): String = prefs.getString("phone", "") ?: ""

    fun saveGovernorate(governorate: String) {
        prefs.edit().putString("governorate", governorate).apply()
    }

    fun getGovernorate(): String = prefs.getString("governorate", "") ?: ""

    fun saveLanguage(language: String) {
        prefs.edit().putString("language", language).apply()
    }

    fun getLanguage(): String = prefs.getString("language", "en") ?: "en"

    fun saveTheme(theme: String) {
        prefs.edit().putString("theme", theme).apply()
    }

    fun getTheme(): String = prefs.getString("theme", "light") ?: "light"

    fun setLoggedIn(loggedIn: Boolean) {
        prefs.edit().putBoolean("logged_in", loggedIn).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("logged_in", false)

    fun logout() {
        prefs.edit().clear().apply()
    }
}
