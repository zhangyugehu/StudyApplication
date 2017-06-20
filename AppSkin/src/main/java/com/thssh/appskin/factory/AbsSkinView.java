package com.thssh.appskin.factory;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * 需要换肤的view对象信息
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public abstract class AbsSkinView<T extends View> {
    private T view;
    private List<SkinItem> items;

    public AbsSkinView(T view, List<SkinItem> items) {
        this.view = view;
        this.items = items;
    }

    public void apply(){

        for (SkinItem item: items){
            if(TextUtils.equals("textColor", item.getAttrName())){
                setTextColor(view, item);
            }else if(TextUtils.equals("background", item.getAttrName())){
                if(TextUtils.equals("drawable", item.getAttrType())){
                    setBackgroundDrawable(view, item);
                }else if(TextUtils.equals("color", item.getAttrType())){
                    setBackgroundColor(view, item);
                }else if(TextUtils.equals("mipmap", item.getAttrType())){
                    setBackgroundMipmap(view, item);
                }
            }
        }
    }

    protected abstract void setBackgroundMipmap(T view, SkinItem item);

    protected abstract void setBackgroundColor(T view, SkinItem item);

    protected abstract void setBackgroundDrawable(T view, SkinItem item);

    protected abstract void setTextColor(T view, SkinItem item);

}
