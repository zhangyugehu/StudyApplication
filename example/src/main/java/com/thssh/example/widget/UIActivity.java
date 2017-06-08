package com.thssh.example.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thssh.example.R;
import com.thssh.example.widget.LinearGradientTextViewActivity;
import com.thssh.example.widget.PercentRelativeLayoutActivity;
import com.thssh.example.widget.ShaderActivity;
import com.thssh.widget.FlowLayout;
import com.thssh.widget.ZoomImageView;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

    }

    public void percentLayoutClick(View view) {
        startActivity(new Intent(this, PercentRelativeLayoutActivity.class));
    }

    public void shaderClick(View view) {
        startActivity(new Intent(this, ShaderActivity.class));
    }

    public void zoomImageClick(View view) {
        startActivity(new Intent(this, ZoomImageActivity.class));
    }

    public void linearGradientTextViewClick(View view) {
        startActivity(new Intent(this, LinearGradientTextViewActivity.class));
    }

    public void roundeImageViewClick(View view) {
        startActivity(new Intent(this, RoundImageActivity.class));
    }

    public void flowLayoutClick(View view) {
        startActivity(new Intent(this, FlowLayoutActivity.class));
    }
}
