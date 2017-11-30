package br.com.twitterapi.xplore.xplorertwitterapi.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.services.StatusesService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.twitterapi.xplore.xplorertwitterapi.converters.factories.TokenAuthorizationConverterFactory;
import br.com.twitterapi.xplore.xplorertwitterapi.converters.factories.TweetsConverterFactory;
import br.com.twitterapi.xplore.xplorertwitterapi.endpoints.EndpointSearchTweets;
import br.com.twitterapi.xplore.xplorertwitterapi.endpoints.EndpointTwitterAuthentication;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.Tweet;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import br.com.twitterapi.xplore.xplorertwitterapi.generator.RetrofitServiceGenerator;
import br.com.twitterapi.xplore.xplorertwitterapi.interceptors.InterceptorSearchTweet;
import br.com.twitterapi.xplore.xplorertwitterapi.interceptors.InterceptorTwitterAuthentication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public interface CallbackSearchTweets {
        void callback(Tweet tweet);
        void callback(List<Tweet> tweets);
    }

    public TwitterAPI() {
        TwitterCore twitterCore = TwitterCore.getInstance();
        //twitterSession = twitterCore.getSessionManager().getActiveSession();
        //twitterAuthToken = twitterSession.getAuthToken();
        this.apiClient = twitterCore.getApiClient();
        this.statusesService = apiClient.getStatusesService();
    }


    public void find() {
        StatusesService statusesService = apiClient.getStatusesService();
        Call<com.twitter.sdk.android.core.models.Tweet> call = statusesService.show(524971209851543553L, null, null, null);
        call.enqueue(new Callback<com.twitter.sdk.android.core.models.Tweet>() {
            @Override
            public void onResponse(@NotNull Call<com.twitter.sdk.android.core.models.Tweet> call, @NotNull Response<com.twitter.sdk.android.core.models.Tweet> response) {
                if(response.isSuccessful()) {
                    com.twitter.sdk.android.core.models.Tweet tweet = response.body();
                    Log.i("TWEET_SUCCESS", tweet.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call<com.twitter.sdk.android.core.models.Tweet> call, @NotNull Throwable t) {
                Log.e("FAILURE_TWITTER_RQ", t.getMessage());
            }
        });
    }

    public void getAuthorization(final CallbackAuthorization callbackAuthorization) {
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

    public void searchOnText(final CallbackSearchTweets callbackSearchTweets, TwitterTokenAuthorization token, String queryString) {
        RetrofitServiceGenerator serviceGenerator = RetrofitServiceGenerator.newInstance(EndpointSearchTweets.class
                , new TweetsConverterFactory(), "https://api.twitter.com/");
        InterceptorSearchTweet interceptor = new InterceptorSearchTweet(token);
        serviceGenerator.addInterceptor(interceptor);

        EndpointSearchTweets endpointSearchTweets = (EndpointSearchTweets) serviceGenerator.getService();

        Call<List<Tweet>> call = endpointSearchTweets.searchWithQueryString(queryString);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(@NonNull Call<List<Tweet>> call, @NonNull Response<List<Tweet>> response) {
                if(response.isSuccessful()) {
                    callbackSearchTweets.callback(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Tweet>> call, @NonNull Throwable t) {
                Log.e("EXCEPTION_REQUEST_TWEET", t.getMessage());
            }
        });

    }
}
