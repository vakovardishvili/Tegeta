package com.example.myapplication.color.data

import com.example.myapplication.color.data.entity.ColorEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ColorsApi {
    @GET("api/colors/new?format=json")
    fun getColors(): Single<List<ColorEntity>>
}