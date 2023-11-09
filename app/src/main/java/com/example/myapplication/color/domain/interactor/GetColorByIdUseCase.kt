package com.example.myapplication.color.domain.interactor

import com.example.myapplication.color.domain.repository.ColorsRepository
import javax.inject.Inject

class GetColorByIdUseCase @Inject constructor(
    private val repository: ColorsRepository
) {
    operator fun invoke(colorId: Long) = repository.getColorById(colorId)
}