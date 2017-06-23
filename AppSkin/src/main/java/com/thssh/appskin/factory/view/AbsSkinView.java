package com.thssh.appskin.factory.view;

import android.text.TextUtils;
import android.view.View;

import com.thssh.appskin.factory.SkinItem;
import com.thssh.appskin.factory.SkinManager;

import java.util.List;

/**
 * 需要换肤的view对象信息
 *
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public abstract class AbsSkinView<T extends View> implements SkinView {
    protected T view;
    private List<SkinItem> items;
    protected SkinManager mSkinManager;

    public AbsSkinView(T view, List<SkinItem> items) {
        this.view = view;
        this.items = items;
        this.mSkinManager = SkinManager.getInstance();
    }

    @Override
    public void apply() {
        for (SkinItem item : items) {
            if (TextUtils.equals("textColor", item.getAttrName())) {
                setTextColor(item);
            } else if (TextUtils.equals("background", item.getAttrName())) {
                if (TextUtils.equals("drawable", item.getAttrType())) {
                    setBackgroundDrawable(item);
                } else if (TextUtils.equals("color", item.getAttrType())) {
                    setBackgroundColor(item);
                } else if (TextUtils.equals("mipmap", item.getAttrType())) {
                    setBackgroundMipmap(item);
                }
            }
        }
    }

    protected abstract void setBackgroundMipmap(SkinItem item);

    protected abstract void setBackgroundColor(SkinItem item);

    protected abstract void setBackgroundDrawable(SkinItem item);

    protected abstract void setBackgroundResouces(SkinItem item);

    protected abstract void setTextColor(SkinItem item);

}
