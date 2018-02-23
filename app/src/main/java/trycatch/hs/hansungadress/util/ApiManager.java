package trycatch.hs.hansungadress.util;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trycatch.hs.hansungadress.event.LoginEvent;
import trycatch.hs.hansungadress.event.SearchEvent;
import trycatch.hs.hansungadress.model.AddressModel;

/**
 * Created by trycatch on 2017. 11. 27..
 */

public class ApiManager {
    public static ApiManager instance;
    private Retrofit retrofit;
    private RetrofitService retrofitService;
    private Util util;
    private String id, pw;
    private boolean retryFlag = false;
    private String retrySearchText;

    public ApiManager(Context mContext) {
        util = Util.getInstance(mContext);
        init();
    }

    private void init(){
        try {
            //통신로그를 확인하기 위한 부분
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //쿠키 메니저의 cookie policy를 변경 합니다.
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

            // Install the all-trusting trust manager

            try {
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                final SSLContext sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();


                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(0, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                        .writeTimeout(0, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                        .readTimeout(0, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                        .cookieJar(new JavaNetCookieJar(cookieManager)) //쿠키메니져 설정
                        .addInterceptor(httpLoggingInterceptor) //http 로그 확인
                        .sslSocketFactory(sslSocketFactory)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();
                                Request.Builder requestBuilder = chain.request().newBuilder();
                                requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                                requestBuilder.addHeader("Cookie", "JSESSIONID=" + util.getCookie());
                                requestBuilder.url(request.url().toString().replace("%25", "%"));
                                return chain.proceed(requestBuilder.build());
                            }
                        })
                        .build();

                retrofit = new Retrofit.Builder().baseUrl("http://info.hansung.ac.kr").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                retrofitService = retrofit.create(RetrofitService.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void login(String id, String pw) {
        this.id = id;
        this.pw = pw;
        new LoginAsync().execute();
    }

    public class LoginAsync extends AsyncTask<Void, Void, Void> {
        boolean isLogin = false;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("id, pw", id + pw);
                Connection conn = Jsoup.connect("https://info.hansung.ac.kr/servlet/s_gong.gong_login_ssl").data("id", id).data("passwd", pw).timeout(0).userAgent("Firefox/2.0.0.6").method(Connection.Method.POST);
                Connection.Response res = conn.execute();

                if(!res.cookies().get("JSESSIONID").equals("")) {
                    util.setCookie(res.cookies());
                    isLogin = true;
                    init();
                    if(retryFlag){
                        retryFlag = false;
                        search(retrySearchText);
                    }
                }
                else {
                    isLogin = false;
                    if(retryFlag){
                        retryFlag = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            EventBus.getDefault().post(new LoginEvent(isLogin));
        }
    }

    public void search(final String as_search) {
        try {
            Log.d("as_search", as_search);
            final String query;
            query = URLEncoder.encode(as_search, "EUC-KR");
            Log.d("query", query);
            Call<ArrayList<AddressModel>> search = retrofitService.search(query);
            search.enqueue(new Callback<ArrayList<AddressModel>>() {
                @Override
                public void onResponse(Call<ArrayList<AddressModel>> call, Response<ArrayList<AddressModel>> response) {
                    Log.d("searchOnResponse", response.isSuccessful() + "");
                    if (response.isSuccessful()) {
                        ArrayList<AddressModel> addressArray = response.body();
                        for(int i = 0; i < addressArray.size(); i++){
                            addressArray.get(i).setOffice(PhoneNumberUtils.formatNumber(addressArray.get(i).getOffice(), Locale.getDefault().getCountry()));
                            addressArray.get(i).setPhone(PhoneNumberUtils.formatNumber(addressArray.get(i).getPhone(), Locale.getDefault().getCountry()));
                        }
                        EventBus.getDefault().post(new SearchEvent(true, addressArray));
                    }
                    else{
                        EventBus.getDefault().post(new SearchEvent(false, null));
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<AddressModel>> call, Throwable t) {
                    t.printStackTrace();
                    EventBus.getDefault().post(new SearchEvent(false, null));
                    retryFlag = true;
                    retrySearchText = as_search;
                    login(util.getId(), util.getPw());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().post(new SearchEvent(false, null));
        }
    }

    public static ApiManager getInstance(Context context) {
        if (instance == null) {
            instance = new ApiManager(context);
        }
        return instance;
    }
}
