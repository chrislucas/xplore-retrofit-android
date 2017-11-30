package br.com.twitterapi.xplore.xplorertwitterapi.converters;

import android.util.Log;

import org.json.JSONObject;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;

/**
 * Created by r028367 on 30/11/2017.
 */

public class TokenAuthorizationConverter {

    public static final String TOKEN_TYPE = "token_type";
    public static final String ACCESS_TOKEN = "access_token";

    public TwitterTokenAuthorization fromJsonToTwitterToken(String json) {
        TwitterTokenAuthorization token = new TwitterTokenAuthorization();
        try {
            JSONObject jsonObject = new JSONObject(json);
            token.setTokenType(jsonObject.getString(TOKEN_TYPE));
            token.setAccessToken(jsonObject.getString(ACCESS_TOKEN));

        } catch (Exception e) {
            Log.e("EXCEPTION_JSON_TOJEN", e.getMessage());
        }
        return token;
    }

}
