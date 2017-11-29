package br.com.xplore.xploreretrofit1.converter;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithubUser;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by r028367 on 29/11/2017.
 */

public class GithubUserServiceConverter implements Converter<ResponseBody, List<GithubUser>> {

    @Override
    public List<GithubUser> convert(@NonNull ResponseBody value) throws IOException {
        return null;
    }
}
