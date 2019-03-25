package trycatch.hs.hansungadress.ui.login

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jsoup.HttpStatusException
import trycatch.hs.hansungadress.data.local.PreferencesManager
import trycatch.hs.hansungadress.data.remote.InfoApiService
import trycatch.hs.hansungadress.ui.BaseViewModel

class LoginViewModel(private val infoApi: InfoApiService, private val prefs: PreferencesManager) : BaseViewModel(), AnkoLogger {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val guideAgree: MutableLiveData<Boolean> = MutableLiveData()

    private val _allConditionClear: MediatorLiveData<Boolean> = MediatorLiveData()
    val allConditionClear: LiveData<Boolean> get() = _allConditionClear

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> get() = _success

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    val guideMsg: Spanned = StringBuilder().apply {
        append("한성대학교 교직원 연락처를 조회할 수 있습니다.<br>업무적인 용도 외에 사용을 금합니다.<br>")
        append("<font color=#000000><strong>타인의 개인정보 </strong></font><font color=#ff0000>유출 시</font><font color=#000000><strong> 개인정보 보호법에 따라<br>처벌</strong></font>")
        append(" 을 받을 수 있음을 알려드립니다.")
    }.toString().run {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            Html.fromHtml(this)
        else
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    }

    init {
        _allConditionClear.addSource(id) {
            _allConditionClear.postValue(checkAllConditionClear())
        }
        _allConditionClear.addSource(password) {
            _allConditionClear.postValue(checkAllConditionClear())
        }
        _allConditionClear.addSource(guideAgree) {
            _allConditionClear.postValue(checkAllConditionClear())
        }
    }

    private fun checkAllConditionClear() = !id.value.isNullOrEmpty() and !password.value.isNullOrEmpty() and (guideAgree.value ?: false)

    fun login() {
        doAsync {
            try {
                val cookies = infoApi.login(id.value, password.value)
                prefs.setInfoCookies(cookies)
                prefs.setLoginStatus(true)
                prefs.setID(id.value!!)
                prefs.setPW(password.value!!)
                _success.postValue(true)
            } catch (e: HttpStatusException) {
                _error.postValue(e)
            }
        }
    }

    fun reLogin() {
        id.value = prefs.getID()
        password.value = prefs.getPW()
        login()
    }
}