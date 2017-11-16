package br.com.xplore.xploreretrofit1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
            githubSearchRepo.searchWithFilter("chrislucas", "own");
        }
    }

    @Override
    public void sendGithubListRepos(List<GithubRepo> list) {
        for(GithubRepo githubRepo : list) {
            Log.i("LIST_GITHUB_REPO", githubRepo.toString());
        }
    }
}
