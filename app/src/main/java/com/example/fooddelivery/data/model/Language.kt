package com.example.fooddelivery.data.model

data class Language(
    val code: String,
    val name: String,
    val nativeName: String,
    val flag: String
)

object AvailableLanguages {
    val languages = listOf(
        Language("ar", "Arabic", "العربية", "🇸🇦"),
        Language("en", "English", "English", "🇬🇧"),
        Language("fr", "French", "Français", "🇫🇷"),
        Language("es", "Spanish", "Español", "🇪🇸"),
        Language("de", "German", "Deutsch", "🇩🇪"),
        Language("it", "Italian", "Italiano", "🇮🇹"),
        Language("pt", "Portuguese", "Português", "🇵🇹"),
        Language("ru", "Russian", "Русский", "🇷🇺"),
        Language("ja", "Japanese", "日本語", "🇯🇵"),
        Language("ko", "Korean", "한국어", "🇰🇷"),
        Language("zh", "Chinese", "中文", "🇨🇳"),
        Language("tr", "Turkish", "Türkçe", "🇹🇷"),
        Language("ur", "Urdu", "اردو", "🇵🇰")
    )
}
