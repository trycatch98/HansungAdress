package trycatch.hs.hansungadress.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import trycatch.hs.hansungadress.data.local.PreferencesManager
import trycatch.hs.hansungadress.data.remote.InfoApiService
import trycatch.hs.hansungadress.data.remote.model.AddressModel
import trycatch.hs.hansungadress.ui.BaseViewModel
import java.util.*

class MainViewModel(private val infoApiService: InfoApiService, private val prefs: PreferencesManager) : BaseViewModel(), AnkoLogger {
    val query: MutableLiveData<String> = MutableLiveData()

    private val _searchResult: MutableLiveData<Vector<AddressModel>> = MutableLiveData()
    val searchResult: LiveData<Vector<AddressModel>> get() = _searchResult

    private val _isSearching: MediatorLiveData<Int> = MediatorLiveData()
    val isSearching: LiveData<Int> get() = _isSearching

    init {
        _isSearching.addSource(searchResult) {
            _isSearching.postValue(
                    if (it.isNullOrEmpty().not()) View.VISIBLE
                    else View.GONE
            )
        }
    }

    fun search() {
        addDisposable(
                infoApiService.search(query.value, prefs.getInfoCookies())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            _searchResult.postValue(it)
                        }){
                            it.printStackTrace()
                        }
        )

        //query.postValue("")
    }

    fun isLoginStatus() = prefs.getLoginStatus()
}