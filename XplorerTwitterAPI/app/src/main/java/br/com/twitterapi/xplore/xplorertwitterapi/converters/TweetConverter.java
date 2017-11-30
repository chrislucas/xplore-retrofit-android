package br.com.twitterapi.xplore.xplorertwitterapi.converters;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.Tweet;

/**
 * Created by r028367 on 30/11/2017.
 */

public class TweetConverter {

    public static final String STAT_USES = "statuses";

    public Tweet fromJsonToTweet(String json) {
        Tweet tweet = new Tweet();
        return tweet;
    }

    public List<Tweet> fromJsonToListTweets(String json) {
        List<Tweet> tweets = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(STAT_USES);
            for(int idx=0; idx<jsonArray.length(); idx++) {
                JSONObject object = jsonArray.getJSONObject(idx);
                Log.i("TWEET_OBJECT", object.toString());
                String id = object.getString("id_str");
                String text = object.getString("text");
                JSONObject jsonTwiiterUserObject = object.getJSONObject("user");
                String idUser = jsonTwiiterUserObject.getString("id_str");
                String name = jsonTwiiterUserObject.getString("name");
                String description = jsonTwiiterUserObject.getString("description");
                String url = jsonTwiiterUserObject.getString("url");
                JSONObject coordinates = object.getJSONObject("coordinates");
                JSONObject geo = object.getJSONObject("geo");
                JSONObject place = object.getJSONObject("place");
            }
        } catch (JSONException excp) {
            Log.e("JSON_EXCEPTION_TWEETS", excp.getMessage());
        }
        return tweets;
    }
}
