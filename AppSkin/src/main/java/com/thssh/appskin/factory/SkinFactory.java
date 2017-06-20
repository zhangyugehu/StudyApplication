package com.thssh.appskin.factory;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public class SkinFactory implements LayoutInflaterFactory {
    private static final String TAG = "SkinFactory";

    private String packages[] = new String[]{
            "android.widget.",
            "android.view.",
            "android.webkit."
    };

    private List<AbsSkinView> mCacheList;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        Log.d(TAG, "onCreateView: name: " + name);
        View view = null;
        mCacheList = new ArrayList<>();
        if(name.contains(".")){
            view = createView(parent, name, context, attrs);
        }else{
            for(String pkg : packages){
                view = createView(parent, pkg + name, context, attrs);
                if(view != null){
                    break;
                }
            }
        }
        parseViewSkin(context, view, attrs);
        return view;
    }

    private void parseViewSkin(Context context, View view, AttributeSet attrs) {
        List<SkinItem> items = new ArrayList<>();
        for(int i = 0; i < attrs.getAttributeCount(); i++){
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);

            if(TextUtils.equals(attributeName, "background") || TextUtils.equals(attributeName, "textColor")){

                int id = Integer.parseInt(attributeValue.replaceAll("^@", ""));
                String entryName = context.getResources().getResourceEntryName(id);
                String typeName = context.getResources().getResourceTypeName(id);
                SkinItem item = new SkinItem(attributeName, id, typeName, entryName);
                items.add(item);
                Log.d(TAG, "parseViewSkin: " + item);
            }
        }

        if(!items.isEmpty()){
            if(view instanceof TextView){
                AbsSkinView skinView = new TextViewSkin((TextView) view, items);
                mCacheList.add(skinView);
                skinView.apply();
            }
        }
    }

    private View createView(View parent, String name, Context context, AttributeSet attrs) {
        try {
            Class clazz = context.getClassLoader().loadClass(name);

            Constructor<? extends View> constructor = clazz.getConstructor(new Class[]{Context.class, AttributeSet.class});
            return constructor.newInstance(context, attrs);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
