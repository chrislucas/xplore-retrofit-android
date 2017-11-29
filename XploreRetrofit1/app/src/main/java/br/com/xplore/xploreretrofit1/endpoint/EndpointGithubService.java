package br.com.xplore.xploreretrofit1.endpoint;

import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithubRepo;
import br.com.xplore.xploreretrofit1.entities.GithubUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by C_Luc on 16/11/2017.
 */

public interface EndpointGithubService {
    @GET("users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String username);

    @GET("users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String username, @Query("type") String type);

    @GET("users/{user}/repos")
    Call<ResponseBody> repos(@Path("user") String username);

    @GET("search/users")
    Call<ResponseBody> searchUsers(@Query(value = "q", encoded = true) String queryString);

    @GET("search/repository")
    Call<ResponseBody> searchRepository(@Query(value = "q", encoded = true) String queryString);

    @GET("search/users")
    Call<List<GithubUser>> getUsers(@Query(value = "q", encoded = true) String queryString);
}