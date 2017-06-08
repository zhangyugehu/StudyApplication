package com.thssh.httpprocessor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.thssh.httpprocessor.impl.HttpDelegater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = (TextView) findViewById(R.id.tv);
        HttpDelegater.get().get("http://www.fk68.net/temp/dn.json",
                null,
                new HttpCallBack<DNResult>() {
                    @Override
                    public void onSuccess(DNResult response) {
                        tv.setText(response.toString());
                    }

                    @Override
                    public void onFailure(String error) {
                        tv.setText(error);
                    }
                });
    }
}
