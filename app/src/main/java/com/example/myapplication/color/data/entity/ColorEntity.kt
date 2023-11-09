package com.example.myapplication.color.data.entity

import androidx.annotation.Keep

@Keep
data class ColorEntity(
    val id: Long,
    val title: String,
    val userName: String,
    val dateCreated: String,
    val hex: String,
    val rbg: ColorRgbEntity?,
    val description: String,
    val url: String,
    val imageUrl: String,
    val badgeUrl: String
)

@Keep
data class ColorRgbEntity(
    val red: Int?,
    val green: Int?,
    val blue: Int?
)