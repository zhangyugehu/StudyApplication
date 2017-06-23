package com.thssh.appskin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.thssh.appskin.factory.SkinFactory;
import com.thssh.appskin.factory.SkinManager;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public class SkinActivity extends Activity {

    private SkinFactory mSkinFactory;
    private SkinManager mSkinManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSkinFactory = new SkinFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mSkinFactory);
        mSkinManager = SkinManager.getInstance();
        mSkinManager.init(this);
    }

    protected void changeSkin(String path){
        mSkinManager.loadSkin(path);
        mSkinFactory.apply();
    }
}
