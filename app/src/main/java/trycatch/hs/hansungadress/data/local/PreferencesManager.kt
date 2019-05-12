package trycatch.hs.hansungadress.data.local

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesManager(private val context: Context) {
    private val PREF_KEY_LOGIN_STATUS = "PREF_KEY_LOGIN_STATUS"

    private val PREF_KEY_ID = "PREF_KEY_ID"

    private val PREF_KEY_PW = "PREF_KEY_PW"

    private val PREF_KEY_INFO_COOKIES = "PREF_KEY_INFO_COOKIES"

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

    fun getID() = prefs.getString(PREF_KEY_ID, "")

    fun setID(id: String) = prefs.edit {
        putString(PREF_KEY_ID, id)
    }

    fun getPW() = prefs.getString(PREF_KEY_PW, "")

    fun setPW(pw: String) = prefs.edit {
        putString(PREF_KEY_PW, pw)
    }

    fun getInfoCookies(): Map<String, String> {
        val turnsType = object : TypeToken<Map<String, String>>() {}.type
        return gson.fromJson<Map<String, String>>(prefs.getString(PREF_KEY_INFO_COOKIES, ""), turnsType)
    }

    fun setInfoCookies(cookies: Map<String, String>) = prefs.edit {
        putString(PREF_KEY_GINFO_COOKIES, gson.toJson(cookies))
    }
}