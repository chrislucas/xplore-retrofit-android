package br.com.xplore.xploreretrofit1.generator;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by C_Luc on 16/11/2017.
 */

public class RetrofitServiceGenerator {


    private static Retrofit.Builder builder= new Retrofit
            .Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();


    private static OkHttpClient okHttpClient;


}
