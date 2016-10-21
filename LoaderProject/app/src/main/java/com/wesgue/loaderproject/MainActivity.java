package com.wesgue.loaderproject;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Member Variables
    private RowListFragment mRowListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set PooListFragment
        newPoolListFragmentInstance();
    }

    // create new RowListFragment Instance
    public void newPoolListFragmentInstance() {

        // Set up PoolListFragment
        mRowListFragment = new RowListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_bottom, mRowListFragment, "Frame Bottom");
        transaction.commit();

    }


}
