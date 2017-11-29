package br.com.xplore.xploreretrofit1.http;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import br.com.xplore.xploreretrofit1.endpoint.EndpointTest;
import br.com.xplore.xploreretrofit1.generator.RetrofitServiceGenerator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by r028367 on 29/11/2017.
 */

public class RequestDynamicURLTest {

    public static void test() {
        String baseURL = "http://172.28.10.148:3000/";
        RetrofitServiceGenerator<EndpointTest> serviceGenerator
                = RetrofitServiceGenerator.newInstance(EndpointTest.class, GsonConverterFactory.create(), baseURL);
        EndpointTest endpointTest = serviceGenerator.getService();
        Call<ResponseBody> call = endpointTest.testDynamicURL(baseURL + "home/");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        Log.i("RESPONSE_BODY", response.body().string());
                    } catch (IOException e) {
                        Log.e("RESPONSE_BODY_EX", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e("ON_FAILURE", t.getMessage());
            }
        });
    }
}
