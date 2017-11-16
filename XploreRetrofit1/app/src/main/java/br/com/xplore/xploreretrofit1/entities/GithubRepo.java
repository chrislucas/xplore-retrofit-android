package br.com.xplore.xploreretrofit1.entities;

import java.util.Locale;

/**
 * Created by C_Luc on 16/11/2017.
 */

public class GithubRepo {
    private int id;
    private String name;

    public GithubRepo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Nome: %s | ID: %d", name, id);
    }
}
