package com.thssh.appskin;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;

import java.io.File;


public class MainActivity extends SkinActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {

        changeSkin(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "skintest.apk");
    }
}
