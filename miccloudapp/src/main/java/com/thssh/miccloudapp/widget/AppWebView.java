package com.thssh.miccloudapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhangyugehu on 2017/5/17.
 */

public class AppWebView extends WebView {
    public static interface Listener{
        void onProgressChanged(WebView view, int newProgress);
        void onPageFinished(WebView view, String url);
    }

    private WebSettings settings;
    private Listener listener;

    public AppWebView(Context context) {
        super(context);
    }

    public AppWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AppWebView setListener(Listener listener){
        this.listener = listener;
        return this;
    }

    public void config() {
        configSetting();
        configClient();
    }

    private void configSetting() {
        settings = getSettings();
        //设置参数
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setJavaScriptEnabled(true);
    }

    private void configClient() {
        setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(listener != null){ listener.onProgressChanged(view, newProgress);}
            }
        });
        setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(listener != null){ listener.onPageFinished(view, url);}
            }
        });
    }
}
