package com.wesgue.loaderproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wesley Gue on 10/20/2016.
 */

public class StringLoader extends AsyncTaskLoader<List<String>> {

    private List<String> cachedData;
    public static final String ACTION = "com.loaders.FORCE";

    public StringLoader(Context context){
        super(context);
    }

    @Override
    protected void onStartLoading() {

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter filter = new IntentFilter(ACTION);
        manager.registerReceiver(broadcastReceiver, filter);

        if(cachedData == null) {

            // forces loadInBackground to be called
            forceLoad();
        }else{

            // cachedData exist. It does not have to reload the data.
            super.deliverResult(cachedData);
        }
    }

    @Override
    public List<String> loadInBackground() {
        Log.e("LOAD","Loading new Data");
        try {
            // simulate database load
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array.items));
        return data;
    }

    @Override
    public void deliverResult(List<String> data) {
        cachedData = data;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();

        // clean up what you have
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    public void loadNewStrings(){
        forceLoad();
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            forceLoad();
        }
    };
}
