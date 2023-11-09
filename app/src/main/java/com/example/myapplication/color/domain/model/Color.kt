package com.example.myapplication.color.domain.model

import androidx.annotation.Keep

@Keep
data class Color(
    val id: Long,
    val title: String,
    val userName: String,
    val dateCreated: String,
    val hex: String,
    val rgb: ColorRgb?,
    val description: String,
    val url: String,
    val imageUrl: String,
    val badgeUrl: String
)

data class ColorRgb(
    val red: Int,
    val green: Int,
    val blue: Int
)