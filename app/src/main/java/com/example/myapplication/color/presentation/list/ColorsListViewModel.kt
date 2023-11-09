package com.example.myapplication.color.presentation.list

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.color.domain.interactor.GetColorsUseCase
import com.example.myapplication.color.presentation.list.model.ColorListModel
import com.example.myapplication.color.presentation.list.model.ColorUiMapper
import com.example.myapplication.color.presentation.list.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ColorsListViewModel @Inject constructor(
    private val getColors: GetColorsUseCase,
    private val colorUiMapper: ColorUiMapper
): ViewModel() {
    private val _colorsLiveData = MutableLiveData<Result<List<ColorListModel>>>()
    val colorsLiveData: LiveData<Result<List<ColorListModel>>> get() = _colorsLiveData

    val compositeDisposable = CompositeDisposable()

    init {
        fetchData()
    }

    @SuppressLint("CheckResult")
    private fun fetchData() {
        getColors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _colorsLiveData.postValue(Result.Loading)
                compositeDisposable.add(it)
            }
            .map {
                it.map(colorUiMapper::convert)
            }
            .subscribe({ colors ->
                _colorsLiveData.postValue(Result.Success(colors))
            }, {
                _colorsLiveData.postValue(Result.Error(it))
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
