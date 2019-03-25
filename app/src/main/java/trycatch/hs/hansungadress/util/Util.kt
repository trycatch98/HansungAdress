//package trycatch.hs.hansungadress.util
//
//import android.content.Context
//import android.content.SharedPreferences
//import android.util.Log
//
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.google.gson.JsonObject
//
//import java.util.HashSet
//
//import okhttp3.OkHttpClient
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import trycatch.hs.hansungadress.model.FavoriteModel
//
///**
// * Created by trycatch on 2017. 11. 27..
// */
//
//class Util(mContext: Context) {
//    private val pref: SharedPreferences
//    private val editor: SharedPreferences.Editor
//    private val gson: Gson
//
//    var id: String
//        get() = pref.getString("id", "")
//        set(id) {
//            editor.putString("id", id)
//            editor.commit()
//        }
//
//    var pw: String
//        get() = pref.getString("pw", "")
//        set(pw) {
//            editor.putString("pw", pw)
//            editor.commit()
//        }
//
//    val cookie: String
//        get() = pref.getString("cookie", "")
//
//    var favorite: FavoriteModel
//        get() {
//            val json = pref.getString("favorite", "")
//            return if (json != "")
//                gson.fromJson(json, FavoriteModel::class.java)
//            else
//                FavoriteModel()
//        }
//        set(favoriteModel) {
//            editor.putString("favorite", gson.toJson(favoriteModel))
//            editor.commit()
//        }
//
//    init {
//        pref = mContext.getSharedPreferences(mContext.packageName, Context.MODE_PRIVATE)
//        editor = pref.edit()
//        gson = GsonBuilder().create()
//    }
//
//    fun setCookie(cookies: Map<String, String>) {
//        editor.putString("cookie", cookies["JSESSIONID"])
//        editor.commit()
//    }
//
//    fun clearCookie() {
//        editor.putString("cookie", "")
//        editor.commit()
//    }
//
//    companion object {
//        var instance: Util? = null
//
//        @Synchronized
//        fun getInstance(context: Context): Util {
//            if (instance == null) {
//                instance = Util(context)
//            }
//            return instance
//        }
//    }
//}
