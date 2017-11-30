package br.com.twitterapi.xplore.xplorertwitterapi.interceptors;

import java.io.IOException;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by r028367 on 30/11/2017.
 */

public class InterceptorSearchTweet implements Interceptor {

    private TwitterTokenAuthorization token;

    public InterceptorSearchTweet(TwitterTokenAuthorization token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();
        Request newRequest = originalRequest.newBuilder()
                .url(originalHttpUrl.newBuilder().build())
                .header("Authorization", "Bearer " + token.getAccessToken())
                .method(originalRequest.method(), originalRequest.body())
                .build();
        return chain.proceed(newRequest);
    }
}
