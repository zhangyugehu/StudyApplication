package com.thssh.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.thssh.example.lib.PicassoTestActivity;
import com.thssh.example.sqlorm.SqlOrmExampleActivity;
import com.thssh.example.widget.UIActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void uiClick(View view) {
        startActivity(new Intent(this, UIActivity.class));
    }

    public void ormClick(View view) {
        startActivity(new Intent(this, SqlOrmExampleActivity.class));
    }

    public void loadImageClick(View view) {
        startActivity(new Intent(this, PicassoTestActivity.class));
    }
}
