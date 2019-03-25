package trycatch.hs.hansungadress.data.remote

import android.os.Build
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jsoup.Connection
import org.jsoup.Jsoup
import trycatch.hs.hansungadress.BuildConfig
import trycatch.hs.hansungadress.data.remote.model.AddressModel
import trycatch.hs.hansungadress.util.SSLConnect
import java.util.*

class InfoApiService : AnkoLogger {
    private val BASE_URL = BuildConfig.INFO_BASE_URL
    private val ENDPOINT_LOGIN = "$BASE_URL/servlet/s_gong.gong_login_ssl"
    private val ENDPOINT_SEARCH = "$BASE_URL/fuz/staffAddr/staff_info.jsp"

    fun login(id: String?, pw: String?): Map<String, String> {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            SSLConnect().apply {
                postHttps(ENDPOINT_LOGIN, 0, 0)
            }

        val response = Jsoup.connect(ENDPOINT_LOGIN).data("id", id).data("passwd", pw).timeout(0).method(Connection.Method.POST).execute()
        return response.cookies()
    }

    fun search(query: String?, cookies: Map<String, String>)= Observable.create<Vector<AddressModel>> { subscriber ->
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
            SSLConnect().apply {
                postHttps(ENDPOINT_SEARCH, 0, 0)
            }
        val response = Jsoup.connect(ENDPOINT_SEARCH).data("as_search", query).cookies(cookies).timeout(0).method(Connection.Method.POST).execute()
        error { response.body() }

        val turnsType = object : TypeToken<Vector<AddressModel>>() {}.type
        val addressVector: Vector<AddressModel> = Gson().fromJson(response.body(), turnsType)
        error { response.body() }
        subscriber.onNext(addressVector)
        subscriber.onComplete()
    }
}