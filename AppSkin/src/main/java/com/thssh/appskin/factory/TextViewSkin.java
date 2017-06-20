package com.thssh.appskin.factory;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/15
 */

public class TextViewSkin extends AbsSkinView<TextView> {

    public TextViewSkin(TextView view, List<SkinItem> items) {
        super(view, items);
    }

    @Override
    protected void setBackgroundMipmap(TextView view, SkinItem item) {

    }

    @Override
    protected void setBackgroundColor(TextView view, SkinItem item) {

    }

    @Override
    protected void setBackgroundDrawable(TextView view, SkinItem item) {

    }

    @Override
    protected void setTextColor(TextView view, SkinItem item) {
        view.setTextColor(Color.WHITE);
    }
}
