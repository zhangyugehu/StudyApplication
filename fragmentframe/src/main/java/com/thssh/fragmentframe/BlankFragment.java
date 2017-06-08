package com.thssh.fragmentframe;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thssh.fragmentframe.exception.NoFunctionException;
import com.thssh.fragmentframe.widget.Functions;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends BaseFragment {

    public static final String TAG = "BlankFragment";
    public static final String FUNC_NPNR = BlankFragment.class.getSimpleName() + "NPNR";
    public static final String FUNC_WPAR = BlankFragment.class.getSimpleName() + "WPAR";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView tvShow = (TextView) view.findViewById(R.id.tv_show);
        Button btnNpnr = (Button) view.findViewById(R.id.btn_npnr);
        btnNpnr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
//                    mFunctions.invokeFunc(FUNC_NPNR);
                    String returnStr = mFunctions.invokeFunc(FUNC_WPAR, "lalala...", String.class);
//                    Toast.makeText(getContext(), returnStr, Toast.LENGTH_SHORT).show();
                    tvShow.setText(returnStr);
                } catch (NoFunctionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
