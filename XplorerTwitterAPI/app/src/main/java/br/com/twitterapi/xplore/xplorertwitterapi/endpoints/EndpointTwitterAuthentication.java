package br.com.twitterapi.xplore.xplorertwitterapi.endpoints;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by r028367 on 29/11/2017.
 */

public interface EndpointTwitterAuthentication {
    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TwitterTokenAuthorization> getAuthorization(@Field("grant_type") String credentials);
}
