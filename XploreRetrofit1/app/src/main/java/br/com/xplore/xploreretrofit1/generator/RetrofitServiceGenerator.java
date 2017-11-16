package br.com.xplore.xploreretrofit1.generator;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C_Luc on 16/11/2017.
 */

public class RetrofitServiceGenerator {

    public static <Clazz> Clazz createServer(Class<Clazz> service
            , String baseURL, Converter.Factory converterFactory) {
        Retrofit.Builder retrofitBuilder = new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(converterFactory);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        return retrofitBuilder.client(okHttpBuilder.build()).build().create(service);
    }

}
