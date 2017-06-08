package com.thssh.appbadge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        huawei(2);
//        xiaomi(this, 3);
//        samsung(13);
//        sony(this, 20);
    }

    private void huawei(int number){
        String launcherClassName = getClass().getName();//启动的Activity完整名称
        Bundle localBundle = new Bundle();//需要存储的数据
        localBundle.putString("package", getPackageName());//包名
        localBundle.putString("class", launcherClassName);
        localBundle.putInt("badgenumber",number);//未读信息条数
        getContentResolver().call(
                Uri.parse("content://com.huawei.android.launcher.settings/badge/"),
                "change badge",null,localBundle);
    }

    private void xiaomi(Context context, int number){
        try{
            Class miuiNotificationClass = Class.forName("android.app.MiuiNotification");
            Object miuiNotification = miuiNotificationClass.newInstance();
            Field field = miuiNotification.getClass().getDeclaredField("messageCount");
            field.setAccessible(true);
            field.set(miuiNotification,String.valueOf(number==0?"":number));
        }catch (Exception e){
            e.printStackTrace();
            // miui6之前
            Intent localIntent = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
            localIntent.putExtra("android.intent.extra.update_application_component_name",context.getPackageName()+"/."+"MainActivity");
            localIntent.putExtra("android.intent.extra.update_application_message_text",String.valueOf(number==0?"":number));
            context.sendBroadcast(localIntent);
        }
    }

    private void samsung(int number){
        String launcherClassName = getClass().getName();
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count",number);
        intent.putExtra("badge_count_package_name",getPackageName());
        intent.putExtra("badge_count_class_name",launcherClassName);
        sendBroadcast(intent);
    }


    private void sony(Context context,int number) {
        boolean isShow = true;
        if ("0".equals(number)) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);//是否显示
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME",getClass().getName() );//启动页
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", number);//数字
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());//包名
        context.sendBroadcast(localIntent);
    }
}
