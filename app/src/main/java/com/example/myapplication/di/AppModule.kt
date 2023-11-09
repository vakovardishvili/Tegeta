package com.example.myapplication.di

import com.example.myapplication.color.data.ColorsApi
import com.example.myapplication.color.data.repository.ColorsRepositoryImpl
import com.example.myapplication.color.domain.repository.ColorsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindColorsRepository(repositoryImpl: ColorsRepositoryImpl): ColorsRepository

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit() = Retrofit.Builder()
            .baseUrl("https://www.colourlovers.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                    .build()
            )
            .build()

        @Provides
        @Singleton
        fun provideColorsApi(retrofit: Retrofit) = retrofit.create(ColorsApi::class.java)
    }
}