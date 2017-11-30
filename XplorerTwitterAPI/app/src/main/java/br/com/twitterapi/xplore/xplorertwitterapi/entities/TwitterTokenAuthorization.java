package br.com.twitterapi.xplore.xplorertwitterapi.entities;

/**
 * Created by r028367 on 30/11/2017.
 */

public class TwitterTokenAuthorization {

    private String tokenType, accessToken;

    public TwitterTokenAuthorization() {}

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
