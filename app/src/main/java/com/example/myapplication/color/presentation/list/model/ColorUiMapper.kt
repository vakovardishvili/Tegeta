package com.example.myapplication.color.presentation.list.model

import com.example.myapplication.color.domain.model.Color
import java.text.SimpleDateFormat
import javax.inject.Inject

class ColorUiMapper @Inject constructor() {
    fun convert(model: Color): ColorListModel = with(model) {
        ColorListModel(
            id = id,
            imageUrl = imageUrl,
            userName = userName,
            createDate = dateCreated
        )
    }
}

