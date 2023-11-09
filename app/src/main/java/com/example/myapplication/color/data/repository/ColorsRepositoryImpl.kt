package com.example.myapplication.color.data.repository

import com.example.myapplication.color.data.ColorsApi
import com.example.myapplication.color.data.entity.mapper.ColorEntityMapper
import com.example.myapplication.color.domain.model.Color
import com.example.myapplication.color.domain.repository.ColorsRepository
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ColorsRepositoryImpl @Inject constructor(
    private val api: ColorsApi,
    private val mapper: ColorEntityMapper
) : ColorsRepository {

    private val colorsCache: AtomicReference<List<Color>> = AtomicReference(emptyList())

    override fun getColors(): Single<List<Color>> {
        return if (colorsCache.get().isEmpty()) {
            api.getColors()
                .map(mapper::convert)
                .doOnSuccess {
                    colorsCache.set(it)
                }
        } else Single.just(colorsCache.get())
    }

    override fun getColorById(colorId: Long): Single<Color> {
        return getColors()
            .map { it.find { it.id == colorId }!! }
    }
}
