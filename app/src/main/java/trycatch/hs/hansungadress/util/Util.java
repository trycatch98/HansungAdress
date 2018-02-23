package trycatch.hs.hansungadress.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trycatch.hs.hansungadress.model.FavoriteModel;

/**
 * Created by trycatch on 2017. 11. 27..
 */

public class Util {
    public static Util instance;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public Util(Context mContext) {
        pref = mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE);
        editor = pref.edit();
        gson = new GsonBuilder().create();
    }

    public void setId(String id){
        editor.putString("id", id);
        editor.commit();
    }

    public String getId(){
        return pref.getString("id", "");
    }

    public void setPw(String pw){
        editor.putString("pw", pw);
        editor.commit();
    }

    public String getPw(){
        return pref.getString("pw", "");
    }

    public void setCookie(Map<String, String> cookies){
        editor.putString("cookie", cookies.get("JSESSIONID"));
        editor.commit();
    }

    public String getCookie(){
        String cookies =  pref.getString("cookie", "");
        return cookies;
    }

    public void clearCookie(){
        editor.putString("cookie", "");
        editor.commit();
    }

    public void setFavorite(FavoriteModel favoriteModel){
        editor.putString("favorite", gson.toJson(favoriteModel));
        editor.commit();
    }

    public FavoriteModel getFavorite(){
        String json = pref.getString("favorite", "");
        if(!json.equals(""))
            return gson.fromJson(json, FavoriteModel.class);
        else
            return new FavoriteModel();
    }

    public static synchronized Util getInstance(Context context) {
        if(instance == null){
            instance = new Util(context);
        }
        return instance;
    }
}
