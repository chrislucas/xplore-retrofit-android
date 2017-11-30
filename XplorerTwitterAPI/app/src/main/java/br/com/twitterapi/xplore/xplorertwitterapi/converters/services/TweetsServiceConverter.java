package br.com.twitterapi.xplore.xplorertwitterapi.converters.services;

import java.io.IOException;
import java.util.List;

import br.com.twitterapi.xplore.xplorertwitterapi.converters.TweetConverter;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.Tweet;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by r028367 on 30/11/2017.
 */

public class TweetsServiceConverter implements Converter<ResponseBody, List<Tweet>> {

    @Override
    public List<Tweet> convert(ResponseBody value) throws IOException {
        String json = value.string();
        TweetConverter tweetConverter = new TweetConverter();
        return tweetConverter.fromJsonToListTweets(json);
    }
}
