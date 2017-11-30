package br.com.twitterapi.xplore.xplorertwitterapi;

import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.twitter.sdk.android.core.Twitter;

import java.util.List;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.Tweet;
import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import br.com.twitterapi.xplore.xplorertwitterapi.http.TwitterAPI;
import br.com.twitterapi.xplore.xplorertwitterapi.utils.device.Device;

public class ActivityStart extends AppCompatActivity implements TwitterAPI.CallbackAuthorization, TwitterAPI.CallbackSearchTweets {

    private TwitterTokenAuthorization twitterTokenAuthorization;
    private TwitterAPI twitterAPI;
    private EditText editTextSearchTweets;
    private View wrapperLayout;


    @Override
    public void callback(TwitterTokenAuthorization tokenAuthorization) {
        this.twitterTokenAuthorization = tokenAuthorization;
        editTextSearchTweets.setEnabled(true);
        Snackbar.make(wrapperLayout, "Tenho há autorização :) !!!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void callback(Tweet tweet) {

    }

    @Override
    public void callback(List<Tweet> tweets) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                editTextSearchTweets.setEnabled(true);
            }
        }, 1500);

        Snackbar.make(wrapperLayout, "Pesquisa finalizada", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * https://dev.twitter.com/twitterkit/android/installation
         * */
        twitterTokenAuthorization = new TwitterTokenAuthorization();
        Twitter.initialize(this);
        setContentView(R.layout.activity_start);
        editTextSearchTweets = findViewById(R.id.edit_text_search);
        wrapperLayout = findViewById(R.id.wrapper_layout_activity_start);
        final TwitterAPI.CallbackSearchTweets callbackSearchTweets = this;
        if(Device.simpleIsConnected(this)) {
            twitterAPI = new TwitterAPI();
            twitterAPI.getAuthorization(this);
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    /**
                     * Do search
                     * */
                    String search = s.toString();
                    if(search.length() > 3) {
                        // DO SOMETHING
                        twitterAPI.searchOnText(callbackSearchTweets, twitterTokenAuthorization, search);
                        editTextSearchTweets.setEnabled(false);
                    }
                }
            };
            editTextSearchTweets.addTextChangedListener(textWatcher);
            editTextSearchTweets.setEnabled(false);
        }
        else {
            Snackbar.make(wrapperLayout
                    , "Não há conexão com a internet", Snackbar.LENGTH_SHORT).show();
        }
    }
}
