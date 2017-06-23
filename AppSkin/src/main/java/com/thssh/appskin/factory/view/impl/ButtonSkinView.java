package com.thssh.appskin.factory.view.impl;

import android.view.View;
import android.widget.Button;

import com.thssh.appskin.factory.SkinItem;
import com.thssh.appskin.factory.view.AbsSkinView;
import com.thssh.appskin.factory.view.SkinView;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/23
 */

public class ButtonSkinView extends AbsSkinView<Button> {


    public ButtonSkinView(Button view, List<SkinItem> items) {
        super(view, items);
    }

    @Override
    protected void setBackgroundMipmap(SkinItem item) {
        view.setBackgroundDrawable(mSkinManager.getMipmap(item.getrId()));
    }

    @Override
    protected void setBackgroundColor(SkinItem item) {
        view.setBackgroundColor(mSkinManager.getColor(item.getrId()));
    }

    @Override
    protected void setBackgroundDrawable(SkinItem item) {
        view.setBackgroundDrawable(mSkinManager.getDrawable(item.getrId()));
    }

    @Override
    protected void setBackgroundResouces(SkinItem item) {
        view.setBackgroundResource(mSkinManager.getDrawableResouces(item.getrId()));
    }

    @Override
    protected void setTextColor(SkinItem item) {
        view.setTextColor(mSkinManager.getColor(item.getrId()));
    }
}
