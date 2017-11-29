package br.com.xplore.xploreretrofit1.http;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import br.com.xplore.xploreretrofit1.endpoint.EndpointGithubService;
import br.com.xplore.xploreretrofit1.entities.GithubRepo;
import br.com.xplore.xploreretrofit1.generator.RetrofitServiceGenerator;
import br.com.xplore.xploreretrofit1.generator.RetrofitServiceGeneratorGithub;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by r028367 on 16/11/2017.
 */

public class GithubSearchRepoImpl {

    private CallbackSearchRepo callbackSearchRepo;

    public GithubSearchRepoImpl(CallbackSearchRepo callbackSearchRepo) {
        this.callbackSearchRepo = callbackSearchRepo;
    }

    public void search(String username) {
        EndpointGithubService endpointGithubService =
                RetrofitServiceGeneratorGithub.createServer(EndpointGithubService.class);
        Call<List<GithubRepo>> call = endpointGithubService.reposForUser(username);
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(@NonNull Call<List<GithubRepo>> call, @NonNull Response<List<GithubRepo>> response) {
                if(response.isSuccessful()) {
                    callbackSearchRepo.sendGithubListRepos(response.body());
                }
                else {
                    Log.e("LIST_GITHUB_REPO", "FAILED");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GithubRepo>> call, @NonNull Throwable t) {
                Log.e("THROWABLE", t.getMessage());
            }
        });
    }

    public void searchWithFilter(String username, String type) {
        EndpointGithubService endpointGithubService = RetrofitServiceGeneratorGithub
                .createServer(EndpointGithubService.class);
        Call<List<GithubRepo>> call = endpointGithubService.reposForUser(username, type);
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(@NonNull Call<List<GithubRepo>> call, @NonNull Response<List<GithubRepo>> response) {
                if(response.isSuccessful()) {
                    callbackSearchRepo.sendGithubListRepos(response.body());
                }
                else {
                    Log.e("LIST_GITHUB_REPO", "FAILED");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GithubRepo>> call, @NonNull Throwable t) {
                Log.e("EXCEPTION_RQ", t.getMessage());
            }
        });
    }

    public void seachAndTestResponseBody(String username) {
        RetrofitServiceGenerator<EndpointGithubService> serviceGenerator = RetrofitServiceGenerator
                .newInstance(EndpointGithubService.class, GsonConverterFactory.create(), "http://api.github.com/");
        EndpointGithubService endPoint = serviceGenerator.getService();
        Call<ResponseBody> call = endPoint.repos(username);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    processInputStream(response.body().byteStream());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.i("EXCEPTION_RQ", t.getMessage());
            }
        });
    }

    private void processInputStream(InputStream inputStream) {
        if(inputStream != null) {
            BufferedReader reader =  new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            try {
                while ( (line = reader.readLine()) != null) {
                    Log.i("RESPONSE_BODY", line);
                }
            }
            catch (IOException e) {
                Log.e("RESPONSE_BODY_EX", e.getMessage());
            }
        }
    }

    public void searchWithQueryString(String queryString) {
        RetrofitServiceGenerator<EndpointGithubService> serviceGenerator = null;
        serviceGenerator = RetrofitServiceGenerator.newInstance(EndpointGithubService.class
                , GsonConverterFactory.create(), "http://api.github.com/");
        Call<ResponseBody> call = serviceGenerator.getService().searchUsers(queryString);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    processInputStream(response.body().byteStream());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.i("EXCEPTION_RQ", t.getMessage());
            }
        });
    }
}
