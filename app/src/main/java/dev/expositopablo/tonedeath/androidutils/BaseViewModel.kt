package dev.expositopablo.tonedeath.androidutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.expositopablo.tonedeath.data.commons.State
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class BaseViewModel : ViewModel() {
    protected val mutableState = MutableLiveData<State>()
    val state: LiveData<State>
    get() = mutableState

    private val compositeDisposable = CompositeDisposable()


    fun dispose(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    inner class BaseDisposableObserver : DisposableObserver<State>() {
        override fun onComplete() {
        }

        override fun onNext(t: State) {
            mutableState.postValue(t)
        }

        override fun onError(e: Throwable) {
            mutableState.postValue(State.ErrorMsg())
        }

    }
}