package com.example.fooddelivery.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "FoodDeliveryPrefs",
        Context.MODE_PRIVATE
    )

    // Language preferences
    fun setLanguage(language: String) {
        prefs.edit().putString("language", language).apply()
    }

    fun getLanguage(): String = prefs.getString("language", "ar") ?: "ar"

    // Theme preferences
    fun setTheme(theme: String) {
        prefs.edit().putString("theme", theme).apply()
    }

    fun getTheme(): String = prefs.getString("theme", "light") ?: "light"

    // Phone authentication
    fun setPhoneNumber(phone: String) {
        prefs.edit().putString("phone_number", phone).apply()
    }

    fun getPhoneNumber(): String = prefs.getString("phone_number", "") ?: ""

    fun setAuthToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    fun getAuthToken(): String = prefs.getString("auth_token", "") ?: ""

    fun isUserLoggedIn(): Boolean = getAuthToken().isNotEmpty()

    // Governorate selection
    fun setSelectedGovernorate(governorate: String) {
        prefs.edit().putString("selected_governorate", governorate).apply()
    }

    fun getSelectedGovernorate(): String = prefs.getString("selected_governorate", "") ?: ""

    // Privacy and terms
    fun setPrivacyAccepted(accepted: Boolean) {
        prefs.edit().putBoolean("privacy_accepted", accepted).apply()
    }

    fun isPrivacyAccepted(): Boolean = prefs.getBoolean("privacy_accepted", false)

    fun setTermsAccepted(accepted: Boolean) {
        prefs.edit().putBoolean("terms_accepted", accepted).apply()
    }

    fun isTermsAccepted(): Boolean = prefs.getBoolean("terms_accepted", false)

    // Notifications
    fun setNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("notifications_enabled", enabled).apply()
    }

    fun isNotificationsEnabled(): Boolean = prefs.getBoolean("notifications_enabled", true)

    // Logout
    fun logout() {
        prefs.edit().apply {
            remove("auth_token")
            remove("phone_number")
        }.apply()
    }
}
