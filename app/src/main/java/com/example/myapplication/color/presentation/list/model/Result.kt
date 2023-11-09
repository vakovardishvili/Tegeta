package com.example.myapplication.color.presentation.list.model

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    object Loading : Result<Nothing>()
    class Error(val throwable: Throwable) : Result<Nothing>()
}