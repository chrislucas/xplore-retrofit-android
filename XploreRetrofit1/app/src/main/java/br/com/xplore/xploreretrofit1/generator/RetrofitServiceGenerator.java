package br.com.xplore.xploreretrofit1.generator;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by C_Luc on 16/11/2017.
 */

public class RetrofitServiceGenerator<T> {

    private Retrofit.Builder retrofitBuilder;
    private T service;
    private static RetrofitServiceGenerator instance;

    private RetrofitServiceGenerator() {}

    public static <T> RetrofitServiceGenerator newInstance(Class<T> service, Converter.Factory converterFactory, String baseURL) {
        if(instance == null) {
            instance = new RetrofitServiceGenerator();
            instance.retrofitBuilder = getRetrofitBuilder(converterFactory, baseURL);
            instance.service = createServer(service);
        }
        return instance;
    }

    public T getService() {
        return service;
    }

    public static void defineBaseURL(String baseURL) {
        instance.retrofitBuilder.baseUrl(baseURL);
    }

    private static Retrofit.Builder getRetrofitBuilder(Converter.Factory converterFactory, String baseURL) {
       return new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(converterFactory);
    }

    private static <T> T createServer(Class<T> service) {
        return instance.retrofitBuilder.client(new OkHttpClient.Builder().build()).build().create(service);
    }

}
