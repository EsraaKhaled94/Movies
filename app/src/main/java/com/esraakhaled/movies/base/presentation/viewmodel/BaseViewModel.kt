package com.esraakhaled.movies.base.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val loadingObservable = MutableLiveData(false)
    fun getLoadingObservable() = loadingObservable as LiveData<Boolean>
    fun addLoadingValue(value: Boolean) {
        if (value == loadingObservable.value) return
        loadingObservable.postValue(value)
    }


    private val errorObservable = MutableLiveData<Throwable?>()
    fun getErrorObservable() = errorObservable as LiveData<Throwable?>
    fun addErrorValue(value: Throwable?) {
        if (value == errorObservable.value) return
        addLoadingValue(false)
        errorObservable.postValue(value)
    }

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}