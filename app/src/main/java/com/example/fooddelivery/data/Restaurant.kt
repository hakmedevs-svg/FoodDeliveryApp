package com.example.fooddelivery.data

import java.io.Serializable

data class Restaurant(
    val id: Int,
    val name: String,
    val cuisine: String,
    val rating: Double,
    val deliveryTime: String,
    val deliveryFee: String,
    val address: String,
    val description: String,
    val latitude: Double,
    val longitude: Double
) : Serializable
