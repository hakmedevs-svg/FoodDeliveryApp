package com.example.fooddelivery.data

import java.io.Serializable

data class Governorate(
    val name: String,
    val city: String,
    val latitude: Double,
    val longitude: Double
) : Serializable

object IraqiGovernorates {
    val list = listOf(
        Governorate("Baghdad", "Baghdad", 33.3152, 44.3661),
        Governorate("Basra", "Basra", 30.5369, 47.8203),
        Governorate("Erbil", "Erbil", 36.1911, 44.0092),
        Governorate("Sulaymaniyah", "Sulaymaniyah", 35.5577, 45.4356),
        Governorate("Kirkuk", "Kirkuk", 35.4681, 44.3922),
        Governorate("Najaf", "Najaf", 31.9974, 44.3152),
        Governorate("Karbala", "Karbala", 32.6160, 44.0249),
        Governorate("Anbar", "Anbar", 33.3627, 43.7738),
        Governorate("Diyala", "Diyala", 33.7722, 45.1487),
        Governorate("Wasit", "Wasit", 32.5128, 45.8431),
        Governorate("Maysan", "Maysan", 31.8730, 47.1367),
        Governorate("Duhok", "Duhok", 36.8675, 42.9888),
        Governorate("Nineveh", "Mosul", 36.3350, 43.1189),
        Governorate("Babel", "Hilla", 32.4644, 44.4200),
        Governorate("Saladin", "Tikrit", 34.5976, 43.6781),
        Governorate("Thi Qar", "Nasiriyah", 31.0496, 46.2572),
        Governorate("Muthanna", "Samawa", 31.3185, 45.2808),
        Governorate("Al-Qadisiyah", "Diwaniyah", 31.9909, 44.9241),
        Governorate("Al-Muthanna", "Samawa", 31.3185, 45.2808)
    )
}
