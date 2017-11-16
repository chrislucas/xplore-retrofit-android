package br.com.xplore.xploreretrofit1.http;

import android.util.Log;

import java.util.List;

import br.com.xplore.xploreretrofit1.endpoint.EndpointGithubService;
import br.com.xplore.xploreretrofit1.entities.GithubRepo;
import br.com.xplore.xploreretrofit1.generator.RetrofitServiceGeneratorGithub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by r028367 on 16/11/2017.
 */

public class GithubSearchRepoImpl {

    private CallbackSearchRepo callbackSearchRepo;

    public GithubSearchRepoImpl(CallbackSearchRepo callbackSearchRepo) {
        this.callbackSearchRepo = callbackSearchRepo;
    }

    public void search(String username) {
        EndpointGithubService endpointGithubService = RetrofitServiceGeneratorGithub
                .createServer(EndpointGithubService.class);
        Call<List<GithubRepo>> call = endpointGithubService.reposForUser(username);
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                if(response.isSuccessful()) {
                    callbackSearchRepo.sendGithubListRepos(response.body());
                }
                else {
                    Log.e("LIST_GITHUB_REPO", "FAILED");
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Log.e("THROWABÇE", t != null ? t.getMessage() : "Sistema incapaz de inforção");
            }
        });
    }

    public void searchWithFilter(String username, String type) {
        EndpointGithubService endpointGithubService = RetrofitServiceGeneratorGithub
                .createServer(EndpointGithubService.class);
        Call<List<GithubRepo>> call = endpointGithubService.reposForUser(username, type);
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                if(response.isSuccessful()) {
                    callbackSearchRepo.sendGithubListRepos(response.body());
                }
                else {
                    Log.e("LIST_GITHUB_REPO", "FAILED");
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Log.e("THROWABÇE", t != null ? t.getMessage() : "Sistema incapaz de inforção");
            }
        });
    }
}
