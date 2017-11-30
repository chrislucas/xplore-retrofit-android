package br.com.twitterapi.xplore.xplorertwitterapi.generator;

import br.com.twitterapi.xplore.xplorertwitterapi.interceptors.InterceptorTwitterAuthentication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by C_Luc on 16/11/2017.
 */

public class RetrofitServiceGenerator<T> {

    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient.Builder okHttpClientBuilder;
    private Class<T> clazz;
    private T service;
    private static RetrofitServiceGenerator instance;

    private RetrofitServiceGenerator() {}

    public static <T> RetrofitServiceGenerator newInstance(Class<T> clazz, Converter.Factory converterFactory, String baseURL) {
        if(instance == null) {
            instance = new RetrofitServiceGenerator();
            instance.clazz = clazz;
            instance.retrofitBuilder = getRetrofitBuilder(converterFactory, baseURL);
            instance.service = createServer(clazz);
        }
        return instance;
    }

    public T getService() {
        return service;
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return okHttpClientBuilder;
    }

    public void redefineBaseURL(String baseURL) {
        retrofitBuilder.baseUrl(baseURL);
        service = retrofitBuilder.client(okHttpClientBuilder.build()).build().create(clazz);
    }

    private static Retrofit.Builder getRetrofitBuilder(Converter.Factory converterFactory
            , String baseURL) {
       return new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(converterFactory);
    }

    public boolean containsInterceptor(Interceptor interceptor) {
        return okHttpClientBuilder.interceptors().contains(interceptor);
    }

    public void addInterceptor(Interceptor interceptor) {
        if(!containsInterceptor(interceptor)) {
            okHttpClientBuilder.addInterceptor(interceptor);
            service = retrofitBuilder.client(instance.okHttpClientBuilder
                    .build()).build().create(clazz);
        }
    }

    private static <T> T createServer(Class<T> service) {
        instance.okHttpClientBuilder = new OkHttpClient.Builder();
        return instance.retrofitBuilder.client(instance.okHttpClientBuilder.build())
                .build().create(service);
    }

}
