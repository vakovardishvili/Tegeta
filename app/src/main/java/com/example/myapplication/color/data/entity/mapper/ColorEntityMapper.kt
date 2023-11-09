package com.example.myapplication.color.data.entity.mapper

import android.annotation.SuppressLint
import com.example.myapplication.color.data.entity.ColorEntity
import com.example.myapplication.color.data.entity.ColorRgbEntity
import com.example.myapplication.color.domain.model.Color
import com.example.myapplication.color.domain.model.ColorRgb
import java.text.SimpleDateFormat
import javax.inject.Inject

class ColorEntityMapper @Inject constructor() {

    fun convert(entities: List<ColorEntity>): List<Color> = entities.map(::convert)

    private fun convert(entity: ColorEntity): Color = with(entity) {
        Color(
            id = id,
            title = title,
            userName = userName,
            dateCreated = formatDate(dateCreated),
            hex = hex,
            rgb = rbg?.let(::convert),
            description = description,
            url = url,
            imageUrl = imageUrl,
            badgeUrl = badgeUrl
        )
    }

    private fun convert(entity: ColorRgbEntity): ColorRgb = with(entity) {
        ColorRgb(
            red = red ?: 0, green = green ?: 0, blue = blue ?: 0
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(originalDateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = inputFormat.parse(originalDateString)
        val outputFormat = SimpleDateFormat("yyyy-MM-dd")
        return date?.let { outputFormat.format(it) } ?: ""
    }
}