package br.com.twitterapi.xplore.xplorertwitterapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.twitterapi.xplore.xplorertwitterapi.http.TwitterAPI;

public class ActivityStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TwitterAPI twitterAPI = new TwitterAPI();
        twitterAPI.find();
    }
}
