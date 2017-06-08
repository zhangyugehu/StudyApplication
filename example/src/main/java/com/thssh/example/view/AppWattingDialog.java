package com.thssh.example.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

import com.thssh.example.R;
import com.thssh.widget.WaveProgressView;

/**
 *
 * TODO 需要添加阴影效果
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/07
 */

public class AppWattingDialog extends Dialog {

    public static class Builder{
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public AppWattingDialog create(){
            return new AppWattingDialog(context);
        }
    }

    private WaveProgressView progressView;

    public AppWattingDialog(@NonNull Context context) {
        this(context, R.style.TipDialog);
    }

    public AppWattingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_watting);
        progressView = (WaveProgressView) findViewById(R.id.progress_view);

    }

    public static Builder newBuilder(Context context){
        return new Builder(context);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(progressView != null){ progressView.reset(); }
    }
}
