package br.com.xplore.xploreretrofit1.interceptors;

import com.wealdtech.hawk.HawkClient;
import com.wealdtech.hawk.HawkCredentials;

import java.io.IOException;
import java.net.URI;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by r028367 on 29/11/2017.
 */

public class HawkAuthenticationInterceptor implements Interceptor{

    private HawkClient hawkClient;

    public HawkAuthenticationInterceptor(HawkCredentials hawkCredentials) {
        this.hawkClient = new HawkClient.Builder()
                .credentials(hawkCredentials).build();

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        URI uri = original.url().uri();
        String method = original.method();
        String header = hawkClient
                .generateAuthorizationHeader(uri, method, null
                        , null, null, null);
        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", header)
                .method(method, original.body());
        return chain.proceed(requestBuilder.build());
    }
}
