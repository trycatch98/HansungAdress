package trycatch.hs.hansungadress.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

open class BaseViewModel : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun addDisposable(d: Disposable) {
        disposable += d
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}