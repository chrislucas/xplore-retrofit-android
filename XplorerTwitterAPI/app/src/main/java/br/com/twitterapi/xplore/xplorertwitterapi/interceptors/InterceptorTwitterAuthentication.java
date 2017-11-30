package br.com.twitterapi.xplore.xplorertwitterapi.interceptors;

import android.util.Base64;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by r028367 on 30/11/2017.
 */

public class InterceptorTwitterAuthentication implements Interceptor {

    private String key, secret;

    public InterceptorTwitterAuthentication(String key, String secret) {
        this.key = key;
        this.secret = secret;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();
        Request newRequest = originalRequest.newBuilder()
                .url(originalHttpUrl.newBuilder().build())
                .header("Authorization", "Basic "+encodeKey())
                .method(originalRequest.method(), originalRequest.body())
                .build();

        return chain.proceed(newRequest);
    }

    private String encodeKey() {
        String token = String.format("%s:%s", key, secret);
        return Base64.encodeToString(token.getBytes(), Base64.NO_WRAP);
    }
}
