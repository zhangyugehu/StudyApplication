package com.thssh.fragmentnormalusage.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    protected final String TAG1 = getClass().getName();
    protected View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        initArgs(args);
        Log.d(TAG1, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG1, "onCreateView: ");
        return inflater.inflate(setLayoutResouces(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG1, "onViewCreated: ");
        mView = view;
        initView(view);
        afterViewInited();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG1, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG1, "onDetach: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG1, "onActivityCreated: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG1, "onDestroy: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG1, "onDestroyView: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG1, "setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG1, "onHiddenChanged: " + hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG1, "onPause: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG1, "onStart: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG1, "onStop: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG1, "onSaveInstanceState: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG1, "onResume: ");
    }

    protected <T extends View> T findViewById(int resid) {
        return (T) mView.findViewById(resid);
    }

    protected abstract void afterViewInited();

    protected abstract void initView(View view);

    protected abstract void initArgs(Bundle args);

    protected abstract int setLayoutResouces();

}
