package br.com.xplore.xploreretrofit1.endpoint;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by r028367 on 29/11/2017.
 */

public interface EndpointTest {
    @GET
    Call<ResponseBody> testDynamicURL(@Url String url);
}
