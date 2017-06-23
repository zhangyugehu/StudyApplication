package com.thssh.appskin.factory;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public class SkinManager {
    private static final SkinManager ourInstance = new SkinManager();

    private final String METHOD_ADD_ASSET_PATH = "addAssetPath";
    private Resources mResources;
    private Context mContext;
    private String mSkinPackageName;

    public static SkinManager getInstance() {
        return ourInstance;
    }

    public void init(Context context){
        this.mContext = context;
    }

    private SkinManager() {
    }

    public void loadSkin(String path){
        PackageManager packageManager = mContext.getPackageManager();
        mSkinPackageName = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES).packageName;

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod(METHOD_ADD_ASSET_PATH, String.class);
            addAssetPath.invoke(assetManager, path);
            mResources = new Resources(assetManager,
                    mContext.getResources().getDisplayMetrics(),
                    mContext.getResources().getConfiguration());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取皮肤包内的指定资源id
     * @param srcRid
     * @param type
     * @return
     */
    private int getSkinResoucesId(int srcRid, String type){
        // 根据资源id获取资源属性名
        String entryName = mContext.getResources().getResourceEntryName(srcRid);
        // 根据自愿属性名获取指定包内的指定资源id
        int skinResid = mContext.getResources().getIdentifier(entryName, type, mSkinPackageName);
        if(skinResid == 0){ return srcRid; }
        return skinResid;
    }

    /**
     * 获取资源包对应的资源id
     * @param resid
     * @return
     */
    public int getColor(int resid){
        if(mResources == null){ return resid; }
        return mResources.getColor(getSkinResoucesId(resid, "color"));
    }

    public Drawable getDrawable(int resid){
        if(mResources == null){ return ContextCompat.getDrawable(mContext, resid); }
        return mResources.getDrawable(getSkinResoucesId(resid, "drawable"));
    }

    public Drawable getMipmap(int resid){
        if(mResources == null){ return ContextCompat.getDrawable(mContext, resid); }
        return mResources.getDrawable(getSkinResoucesId(resid, "mipmap"));
    }

    public int getDrawableResouces(int resid) {
        if(mResources == null){ return resid; }
        return getSkinResoucesId(resid, "drawable");
    }

    public int getMipmapResouces(int resid) {
        if(mResources == null){ return resid; }
        return getSkinResoucesId(resid, "mipmap");
    }
}
