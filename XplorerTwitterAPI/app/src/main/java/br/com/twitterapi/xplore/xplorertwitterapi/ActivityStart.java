package br.com.twitterapi.xplore.xplorertwitterapi;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.twitter.sdk.android.core.Twitter;

import br.com.twitterapi.xplore.xplorertwitterapi.entities.TwitterTokenAuthorization;
import br.com.twitterapi.xplore.xplorertwitterapi.http.TwitterAPI;
import br.com.twitterapi.xplore.xplorertwitterapi.utils.device.Device;

public class ActivityStart extends AppCompatActivity implements TwitterAPI.CallbackAuthorization {

    private TwitterTokenAuthorization twitterTokenAuthorization;
    private TwitterAPI twitterAPI;
    @Override
    public void callback(String response) {

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
        EditText editText = findViewById(R.id.edit_text_search);


        if(Device.simpleIsConnected(this)) {
            twitterAPI = new TwitterAPI(this);
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
                        twitterAPI.searchOnText(search, twitterTokenAuthorization.getAccessToken());
                    }
                }
            };
            editText.addTextChangedListener(textWatcher);
        }
        else {
            Snackbar.make(findViewById(R.id.wrapper_layout_activity_start)
                    , "Não há conexão com a internet", Snackbar.LENGTH_SHORT).show();
        }
    }
}
