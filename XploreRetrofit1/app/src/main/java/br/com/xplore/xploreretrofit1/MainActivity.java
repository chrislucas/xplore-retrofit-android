package br.com.xplore.xploreretrofit1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithubRepo;
import br.com.xplore.xploreretrofit1.http.CallbackSearchRepo;
import br.com.xplore.xploreretrofit1.http.GithubSearchRepoImpl;
import br.com.xplore.xploreretrofit1.utils.device.Device;

public class MainActivity extends AppCompatActivity implements CallbackSearchRepo {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Device.simpleIsConnected(this.getApplicationContext())) {
            GithubSearchRepoImpl githubSearchRepo = new GithubSearchRepoImpl(this);
            //githubSearchRepo.searchWithFilter("chrislucas", "own");
            //githubSearchRepo.seachAndTestResponseBody("chrislucas");
            String queriesString [] = {
                 "chrisluc+repos:>1"
                ,"chrislucas"
                ,"chrisl"
                ,"chrisluc+repos:>1+language:java"
            };
            /**
             * https://api.github.com/search/users?q=chrislucas
             * https://stackoverflow.com/questions/10786042/java-url-encoding-of-query-string-parameters
             * */
            /*
            try {
                githubSearchRepo.searchWithQueryString(URLEncoder.encode(queriesString[0], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
               Log.e("UNSUPPORTED_ENCODING", e.getMessage());
            }
            */
            githubSearchRepo.searchWithQueryString(queriesString[0]);
        }
    }

    @Override
    public void sendGithubListRepos(List<GithubRepo> list) {
        for(GithubRepo githubRepo : list) {
            Log.i("LIST_GITHUB_REPO", githubRepo.toString());
        }
    }
}
