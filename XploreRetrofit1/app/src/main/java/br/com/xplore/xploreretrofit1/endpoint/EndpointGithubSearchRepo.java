package br.com.xplore.xploreretrofit1.endpoint;

import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithubUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by r028367 on 29/11/2017.
 */

public interface EndpointGithubSearchRepo {

    @GET("search/users")
    Call<ResponseBody> searchUsers(@Query(value = "q", encoded = true) String queryString);

    @GET("search/repository")
    Call<ResponseBody> searchRepository(@Query(value = "q", encoded = true) String queryString);

    @GET("search/users")
    Call<List<GithubUser>> getUsers(@Query(value = "q", encoded = true) String queryString);
}
