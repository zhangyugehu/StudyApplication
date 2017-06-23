package com.thssh.fragmentnormalusage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.thssh.fragmentnormalusage.fragment.BaseFragment;
import com.thssh.fragmentnormalusage.fragment.FirstFragment;
import com.thssh.fragmentnormalusage.fragment.SecondFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstFragment = FirstFragment.newInstance("first");
        mFirstFragment.setListener(new FirstFragment.Listener() {

            @Override
            public void openSecondFragment(String param) {
                if(mSecondFragment == null){ mSecondFragment = SecondFragment.newInstance(param); }
                mSecondFragment.addParam(param);
                addFragment(mSecondFragment, SecondFragment.TAG, true);
            }
        });
        addFragment(mFirstFragment, FirstFragment.TAG, false);
    }

    private void addFragment(BaseFragment fragment, String tag, boolean addToStrack){
        Fragment tagFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if(tagFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                    .add(R.id.fragment_container, fragment, tag);
            if(addToStrack) {
                transaction.addToBackStack(tag);
            }
            transaction.commit();
        }else{
            Toast.makeText(this, "already added.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            popStrack();
            Toast.makeText(this, "all " + getSupportFragmentManager().getFragments().size(), Toast.LENGTH_SHORT).show();
        }else{
            super.onBackPressed();
        }
    }

    private void popStrack() {
        getSupportFragmentManager().popBackStack();
    }

    public void getFragments(View view) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Toast.makeText(this, "all " + fragments.size(), Toast.LENGTH_SHORT).show();
    }
}
