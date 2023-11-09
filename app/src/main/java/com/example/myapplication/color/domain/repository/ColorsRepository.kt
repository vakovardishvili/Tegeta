package com.example.myapplication.color.domain.repository

import com.example.myapplication.color.domain.model.Color
import io.reactivex.rxjava3.core.Single

interface ColorsRepository {
    fun getColors(): Single<List<Color>>
    fun getColorById(colorId: Long): Single<Color>
}