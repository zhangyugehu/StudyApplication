package com.thssh.fragmentframe.widget;

import android.text.TextUtils;

import com.thssh.fragmentframe.exception.NoFunctionException;

import java.util.HashMap;

/**
 * Created by zhangyugehu on 2017/5/17.
 */

public class Functions {

    private HashMap<String, FunctionNoParamNoReturn> mFunctionNoParamNoReturnMap;
    private HashMap<String, FunctionWithParamNoReturn> mFunctionWithParamNoReturnMap;
    private HashMap<String, FunctionNoParamWithReturn> mFunctionNoParamWithReturnMap;
    private HashMap<String, FunctionWithParamAndReturn> mFunctionWithParamAndReturnMap;

    public static class Function{
        String functionName;

        public Function(String functionName) {
            this.functionName = functionName;
        }
    }

    // 无参无返回值
    public static abstract class FunctionNoParamNoReturn extends Function{

        public FunctionNoParamNoReturn(String functionName) {
            super(functionName);
        }

        public abstract  void function();
    }
    public Functions addFunction(FunctionNoParamNoReturn function){
        if(function != null){
            if(mFunctionNoParamNoReturnMap == null){ mFunctionNoParamNoReturnMap = new HashMap<>(); }
            mFunctionNoParamNoReturnMap.put(function.functionName, function);
        }
        return this;
    }
    public void invokeFunc(String funcName) throws NoFunctionException {
        if(TextUtils.isEmpty(funcName)){ return; }
        if(mFunctionNoParamNoReturnMap == null){ return; }
        FunctionNoParamNoReturn func = mFunctionNoParamNoReturnMap.get(funcName);
        if(func == null){ throw new NoFunctionException("has no Function: " + funcName + " found in mFunctionNoParamNoReturnMap"); }
        func.function();
    }

    // 有参无返回值
    public static abstract class FunctionWithParamNoReturn<Param> extends Function{

        public FunctionWithParamNoReturn(String functionName) {
            super(functionName);
        }

        public abstract void function(Param param);
    }
    public Functions addFunction(FunctionWithParamNoReturn function){
        if(function != null){
            if(mFunctionWithParamNoReturnMap == null){ mFunctionWithParamNoReturnMap = new HashMap<>(); }
            mFunctionWithParamNoReturnMap.put(function.functionName, function);
        }
        return this;
    }
    public <Param> void invokeFunc(String funcName, Param param) throws NoFunctionException {
        if(TextUtils.isEmpty(funcName)){ return; }
        if(mFunctionWithParamNoReturnMap == null){ return; }
        FunctionWithParamNoReturn func = mFunctionWithParamNoReturnMap.get(funcName);
        if(func == null){ throw new NoFunctionException("has no Function: " + funcName + " found in mFunctionWithParamNoReturnMap"); }
        func.function(param);
    }

    // 无参有返回值
    public static abstract class FunctionNoParamWithReturn<Return> extends Function{

        public FunctionNoParamWithReturn(String functionName) {
            super(functionName);
        }

        public abstract Return function();
    }
    public Functions addFunction(FunctionNoParamWithReturn function){
        if(function != null){
            if(mFunctionNoParamWithReturnMap == null){ mFunctionNoParamWithReturnMap = new HashMap<>(); }
            mFunctionNoParamWithReturnMap.put(function.functionName, function);
        }
        return this;
    }
    public <Return> Return invokeFunc(String funcName, Class<Return> c) throws NoFunctionException {
        if(TextUtils.isEmpty(funcName)){ return null; }
        if(mFunctionNoParamWithReturnMap == null){ return null; }
        FunctionNoParamWithReturn func = mFunctionNoParamWithReturnMap.get(funcName);
        if(func == null){ throw new NoFunctionException("has no Function: " + funcName + " found in mFunctionNoParamWithReturnMap"); }
        if(c == null){ return (Return) func.function();}
        return c.cast(func.function()); // 安全的强转
    }

    // 有参有返回值
    public static abstract class FunctionWithParamAndReturn<Param, Return> extends Function{

        public FunctionWithParamAndReturn(String functionName) {
            super(functionName);
        }

        public abstract Return function(Param param);
    }
    public Functions addFunction(FunctionWithParamAndReturn function){
        if(function != null){
            if(mFunctionWithParamAndReturnMap == null){ mFunctionWithParamAndReturnMap = new HashMap<>(); }
            mFunctionWithParamAndReturnMap.put(function.functionName, function);
        }
        return this;
    }
    public <Param, Return> Return invokeFunc(String funcName, Param param, Class<Return> c) throws NoFunctionException {
        if(TextUtils.isEmpty(funcName)){ return null; }
        if(mFunctionWithParamAndReturnMap == null){ return null; }
        FunctionWithParamAndReturn func = mFunctionWithParamAndReturnMap.get(funcName);
        if(func == null){ throw new NoFunctionException("has no Function: " + funcName + " found in mFunctionWithParamAndReturnMap"); }
        if(c == null){ return (Return) func.function(param);}
        return c.cast(func.function(param)); // 安全的强转
    }
}
