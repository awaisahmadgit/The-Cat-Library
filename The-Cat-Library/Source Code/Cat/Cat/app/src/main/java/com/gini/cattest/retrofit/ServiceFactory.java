package com.gini.cattest.retrofit;

import android.content.Context;
import android.util.Log;

import com.gini.cattest.BuildConfig;
import com.gini.cattest.retrofit.Gson.GsonConverterFactory;
import com.gini.cattest.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class ServiceFactory {

    private static final int HTTP_READ_TIMEOUT = 10000;
    private static final int HTTP_CONNECT_TIMEOUT = 6000;

    public static ApiInterface create(Context context) {
        return create(context, getOkHttpClient(context), AppHelper.getGson());
    }

    private static ApiInterface create(Context context, OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit.create(ApiInterface.class);
    }


    public static OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
//        httpClientBuilder.addInterceptor(makeLoggingInterceptor());
        httpClientBuilder.addInterceptor(new LogJsonInterceptor());
        httpClientBuilder.cache(new Cache(context.getCacheDir(), 25 * 1024 * 1024));

        return httpClientBuilder.build();
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(AppHelper.isDebugMode() ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    public static class LogJsonInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("X-API-Key", Constants.X_API_KEY)
                    .method(original.method(), original.body())
                    .build();

            Response response = chain.proceed(request);
            String rawJson = response.body().string();

            Log.i("VERIFICATION_DIALOG-J", String.format("raw JSON response is: %s", rawJson));

            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), rawJson)).build();
        }
    }
}
