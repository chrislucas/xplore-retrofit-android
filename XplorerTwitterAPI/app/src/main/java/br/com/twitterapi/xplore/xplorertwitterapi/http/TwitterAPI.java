package br.com.twitterapi.xplore.xplorertwitterapi.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import org.jetbrains.annotations.NotNull;

import br.com.twitterapi.xplore.xplorertwitterapi.R;
import br.com.twitterapi.xplore.xplorertwitterapi.converters.factories.TokenAuthorizationConverterFactory;
import br.com.twitterapi.xplore.xplorertwitterapi.converters.services.TokenAuthorizationServiceConverter;
import br.com.twitterapi.xplore.xplorertwitterapi.endpoints.EndpointTwitterAuthentication;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.Twitte;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import br.com.twitterapi.xplore.xplorertwitterapi.generator.RetrofitServiceGenerator;
import br.com.twitterapi.xplore.xplorertwitterapi.interceptors.InterceptorTwitterAuthentication;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by r028367 on 29/11/2017.
 */

public class TwitterAPI {
    private TwitterApiClient apiClient;
    private StatusesService statusesService;
    private TwitterSession twitterSession;
    private TwitterAuthToken twitterAuthToken;
    private CallbackAuthorization callbackAuthorization;

    public interface CallbackAuthorization {
        void callback(TwitterTokenAuthorization tokenAuthorization);
    }

    public interface CallbackSearchTwitte {
        void callback(Twitte twitte);
    }

    public TwitterAPI(CallbackAuthorization callbackAuthorization) {
        TwitterCore twitterCore = TwitterCore.getInstance();
        //twitterSession = twitterCore.getSessionManager().getActiveSession();
        //twitterAuthToken = twitterSession.getAuthToken();
        this.apiClient = twitterCore.getApiClient();
        this.statusesService = apiClient.getStatusesService();
        this.callbackAuthorization = callbackAuthorization;
    }


    public void find() {
        StatusesService statusesService = apiClient.getStatusesService();
        Call<Tweet> call = statusesService.show(524971209851543553L, null, null, null);
        call.enqueue(new Callback<Tweet>() {
            @Override
            public void onResponse(@NotNull Call<Tweet> call, @NotNull Response<Tweet> response) {
                if(response.isSuccessful()) {
                    Tweet tweet = response.body();
                    Log.i("TWEET_SUCCESS", tweet.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Tweet> call, @NotNull Throwable t) {
                Log.e("FAILURE_TWITTER_RQ", t.getMessage());
            }
        });
    }

    public void getAuthorization(Context context) {
        RetrofitServiceGenerator<EndpointTwitterAuthentication> serviceGenerator =
                RetrofitServiceGenerator.newInstance(EndpointTwitterAuthentication.class
                        , new TokenAuthorizationConverterFactory(),"https://api.twitter.com/");
        InterceptorTwitterAuthentication interceptorAuthentication
                = new InterceptorTwitterAuthentication("pJFxr50vRtaPhBGhRLAzBX5A5"
                , "Uwe9t434RUktnsGkIe2UQAclM7cj70FAZuHXr4b4RaIspDZ7rp");
        serviceGenerator.addInterceptor(interceptorAuthentication);
        Call<TwitterTokenAuthorization> call = serviceGenerator.getService().getAuthorization("client_credentials");
        call.enqueue(new Callback<TwitterTokenAuthorization>() {
            @Override
            public void onResponse(@NonNull Call<TwitterTokenAuthorization> call, @NonNull Response<TwitterTokenAuthorization> response) {
                if(response.isSuccessful()) {
                    TwitterTokenAuthorization tokenAuthorization = response.body();
                    callbackAuthorization.callback(tokenAuthorization);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TwitterTokenAuthorization> call, @NonNull Throwable t) {
                Log.e("FAILURE_TWITTER_RQ", t.getMessage());
            }
        });
    }

    public void searchOnText(String token, String stringSearch) {

    }
}
