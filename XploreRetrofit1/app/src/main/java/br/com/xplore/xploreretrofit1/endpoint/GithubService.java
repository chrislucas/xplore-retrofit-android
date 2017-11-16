package br.com.xplore.xploreretrofit1.endpoint;

import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithunRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by C_Luc on 16/11/2017.
 */

public interface GithubService {

    @GET("users/{user}/repos")
    Call<List<GithunRepo>> reposForUser(@Path("user") String username);

}