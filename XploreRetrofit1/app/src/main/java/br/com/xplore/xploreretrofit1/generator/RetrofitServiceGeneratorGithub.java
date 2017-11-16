package br.com.xplore.xploreretrofit1.generator;

import com.wealdtech.hawk.HawkClientConfiguration;
import com.wealdtech.hawk.HawkCredentials;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by r028367 on 16/11/2017.
 */

public class RetrofitServiceGeneratorGithub {
    private static final String BASE_URL = "http://api.github.com/";
    private static final Retrofit.Builder retrofitBuilder = new Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

    private static Retrofit retrofit = retrofitBuilder
            .client(okHttpBuilder.build()).build();

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    public static <Clazz> Clazz createServer(Class<Clazz> serviceClass) {
        if( ! okHttpBuilder.interceptors().contains(loggingInterceptor) ) {
            okHttpBuilder.addInterceptor(loggingInterceptor);
            retrofit = retrofitBuilder
                    .client(okHttpBuilder.build()).build();
        }
        return retrofit.create(serviceClass);
    }


    /**
     * Hawk auth
     * https://futurestud.io/tutorials/retrofit-2-hawk-authentication-on-android
     * */
    public static <Clazz> Clazz createServerWithCredentials(Class<Clazz> serviceClass
            , HawkCredentials credentials) {
        if(credentials != null) {

        }
        return retrofit.create(serviceClass);
    }
}
