package com.example.qunlphngtr.model

data class Room(
    val id: Int,
    val name: String,
    val price: Double,
    val area: Double,
    val status: String,
    val description: String,
    val imageUri: String? = null
)