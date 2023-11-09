package com.example.myapplication.color.presentation.detail

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.color.domain.interactor.GetColorByIdUseCase
import com.example.myapplication.color.domain.model.Color
import com.example.myapplication.color.presentation.list.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ColorDetailsViewModel @Inject constructor(
    private val getColor: GetColorByIdUseCase
) : ViewModel() {
    private val _colorsLiveData = MutableLiveData<Result<Color>>()
    val colorsLiveData: LiveData<Result<Color>> get() = _colorsLiveData
    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun fetchData(id: Long) {
        getColor(id)
            .doOnSubscribe {
                _colorsLiveData.postValue(Result.Loading)
                compositeDisposable.add(it)
            }
            .subscribe({ color ->
                _colorsLiveData.postValue(Result.Success(color))
            }, {
                _colorsLiveData.postValue(Result.Error(it))
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
