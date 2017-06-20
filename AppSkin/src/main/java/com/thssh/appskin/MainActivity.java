package com.thssh.appskin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


public class MainActivity extends SkinActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
    }
}
