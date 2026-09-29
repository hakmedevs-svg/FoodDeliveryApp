package com.example.fooddelivery.data.model

data class User(
    val id: String = "",
    val phoneNumber: String = "",
    val name: String = "",
    val email: String = "",
    val governorate: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
