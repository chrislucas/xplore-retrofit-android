package br.com.twitterapi.xplore.xplorertwitterapi.http;

import android.util.Log;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by r028367 on 29/11/2017.
 */

public class TwitterAPI {

    private TwitterApiClient apiClient;
    private StatusesService statusesService;
    public TwitterAPI() {
        apiClient = TwitterCore.getInstance().getApiClient();
        statusesService = apiClient.getStatusesService();
    }


    public void find() {
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

            }
        });
    }
}
