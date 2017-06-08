package com.thssh.fragmentframe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.thssh.fragmentframe.widget.Functions;

public class MainActivity extends AppCompatActivity {

    private Functions functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initFunctions(){
        functions = new Functions();
        functions.addFunction(new Functions.FunctionNoParamNoReturn(BlankFragment.FUNC_NPNR){

            @Override
            public void function() {
                Toast.makeText(MainActivity.this, "无参无返回值调用", Toast.LENGTH_SHORT).show();
            }
        })
        .addFunction(new Functions.FunctionWithParamAndReturn<String, String>(BlankFragment.FUNC_WPAR){
            @Override
            public String function(String s) {

                String you = "you say " + s + "\n i say hahaha~~~";
                return you;
            }
        });
    }

    public void bindFunctions(String tag){
        FragmentManager fm = getSupportFragmentManager();
        if(TextUtils.equals(BlankFragment.TAG, tag)) {
            BlankFragment fragment = (BlankFragment) fm.findFragmentByTag(tag);
            fragment.setFunctions(functions);
        }
    }
}
