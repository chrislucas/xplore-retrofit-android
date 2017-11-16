package br.com.xplore.xploreretrofit1.http;

import java.util.List;

import br.com.xplore.xploreretrofit1.entities.GithubRepo;

/**
 * Created by r028367 on 16/11/2017.
 */

public interface CallbackSearchRepo {
    void sendGithubListRepos(List<GithubRepo> list);
}
