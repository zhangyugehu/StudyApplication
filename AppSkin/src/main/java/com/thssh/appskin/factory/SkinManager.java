package com.thssh.appskin.factory;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public class SkinManager {
    private static final SkinManager ourInstance = new SkinManager();
    private Resources mResources;
    private Context mContext;
    private String mSkinPackage;

    public static SkinManager getInstance() {
        return ourInstance;
    }

    private SkinManager() {
    }

    public void loadSkin(String path){
        PackageManager packageManager = mContext.getPackageManager();
        mSkinPackage = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES).packageName;
    }
}
