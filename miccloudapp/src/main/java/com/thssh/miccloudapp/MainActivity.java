package com.thssh.miccloudapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thssh.miccloudapp.widget.AppWebView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AppWebView wvView;
    private TextView tvLocation;
    private EditText etUrlInput;
    private ProgressBar pbProgress;

    private WebSettings settings;
    private String mUrl = "file:///android_asset/web.html";

    private LocationHelper.GPS gps;

    @SuppressLint("AddJavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wvView = (AppWebView) findViewById(R.id.wv_view);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        etUrlInput = (EditText) findViewById(R.id.et_url_input);
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        LocationHelper locationHelper = new LocationHelper(this);
        locationHelper.getGPS(new LocationHelper.Listener() {
            @Override
            public void onChanged(LocationHelper.GPS gps) {
                tvLocation.setText("位置信息：" + gps.latitude + ", " + gps.longitude);
                MainActivity.this.gps = gps;
            }

            @Override
            public void onFailure(Exception e) {
                tvLocation.setText("获取位置信息出错");
            }
        });
        wvView.setListener(new AppWebView.Listener(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    if (pbProgress.getVisibility() == View.GONE) {
                        pbProgress.setVisibility(View.VISIBLE);
                    }
                    pbProgress.setProgress(newProgress);
                } else {
                    pbProgress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                etUrlInput.setText(url);
                pbProgress.setVisibility(View.GONE);
            }
        }).config();


        etUrlInput.setText(mUrl);

        wvView.addJavascriptInterface(MainActivity.this, "android");
        wvView.loadUrl(mUrl);
    }

    public void gotoClick(View view) {
        mUrl = etUrlInput.getText().toString().trim();
        wvView.loadUrl(mUrl);
    }

    @Override
    public void onBackPressed() {
        if (wvView.canGoBack()) {
            wvView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @JavascriptInterface
    public void startFunction() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });
    }
    @JavascriptInterface
    public String getReturn() {
        return gps.longitude + ", " + gps.latitude;
    }
}
