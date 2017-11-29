package br.com.xplore.xploreretrofit1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.xplore.xploreretrofit1.http.RequestDynamicURLTest;

public class ActitityTestDynamicURL extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitity_test_dynamic_url);
        RequestDynamicURLTest.test();
    }
}
