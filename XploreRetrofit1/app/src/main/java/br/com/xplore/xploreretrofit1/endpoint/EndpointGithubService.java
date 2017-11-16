package br.com.xplore.xploreretrofit1.endpoint;

import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by C_Luc on 16/11/2017.
 */

public interface EndpointGithubService {
    @GET("users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String username);

    @GET("users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String username, @Query("type") String type);
}