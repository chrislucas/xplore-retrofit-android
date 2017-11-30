package br.com.twitterapi.xplore.xplorertwitterapi.converters.services;

import java.io.IOException;

import br.com.twitterapi.xplore.xplorertwitterapi.converters.TokenAuthorizationConverter;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by r028367 on 30/11/2017.
 */

public class TokenAuthorizationServiceConverter implements Converter<ResponseBody, TwitterTokenAuthorization> {

    @Override
    public TwitterTokenAuthorization convert(ResponseBody value) throws IOException {
        String json = value.string();
        TokenAuthorizationConverter tokenAuthorizationConverter = new TokenAuthorizationConverter();
        return tokenAuthorizationConverter.fromJsonToTwitterToken(json);
    }
}
