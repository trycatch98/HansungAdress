package trycatch.hs.hansungadress.util;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import trycatch.hs.hansungadress.model.AddressModel;

/**
 * Created by trycatch on 2017. 11. 27..
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST("/servlet/s_gong.gong_login_ssl")
    Call<ResponseBody> login(@Field("id") String id, @Field("passwd") String passwd);

    @POST("/fuz/staffAddr/staff_info.jsp")
    Call<ArrayList<AddressModel>> search(@Query("as_search") String as_search);
}
