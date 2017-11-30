package br.com.twitterapi.xplore.xplorertwitterapi.endpoints;

import java.util.List;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.Tweet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by r028367 on 30/11/2017.
 */

public interface EndpointSearchTweets {
    @GET("/1.1/search/tweets.json")
    Call<List<Tweet>> searchWithQueryString(@Query("q") String queryString);
}
