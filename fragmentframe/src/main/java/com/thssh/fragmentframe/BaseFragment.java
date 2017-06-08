package com.thssh.fragmentframe;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thssh.fragmentframe.widget.Functions;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    protected Functions mFunctions;
    public void setFunctions(Functions functions){
        this.mFunctions = functions;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  MainActivity){
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.initFunctions();
            mainActivity.bindFunctions(getTag());
        }
    }

}
