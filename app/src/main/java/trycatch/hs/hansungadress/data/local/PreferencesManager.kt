package trycatch.hs.hansungadress.data.local

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson

class PreferencesManager(private val context: Context) {
    private val PREF_KEY_LOGIN_STATUS = "PREF_KEY_LOGIN_STATUS"

    private val prefs by lazy {
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    private val gson by lazy {
        Gson()
    }

    fun getLoginStatus() = prefs.getBoolean(PREF_KEY_LOGIN_STATUS, false)

    fun setLoginStatus(status: Boolean) = prefs.edit {
        putBoolean(PREF_KEY_LOGIN_STATUS, status)
    }
}