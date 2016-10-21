package com.wesgue.loaderproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wesley Gue on 10/20/2016.
 */

public class LoaderAdapter extends RecyclerView.Adapter<LoaderAdapter.ViewHolder>{

    // Member Variables
    private Context mContext;
    private LayoutInflater mInflater;
    List<RowInfo> mData = Collections.emptyList();


    // Constructor
    public LoaderAdapter(Context context) {

        if(context != null) {
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.row, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final RowInfo current = mData.get(position);

        // Sets up the View with data from database.
        holder.title.setText(current.title);

    }


    @Override
    public int getItemCount() {
        if(mData!=null) {
            return mData.size();
        }else{
            return 0;
        }
    }


    // Gets it's fields from pool_row.xml
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title; // title of row


        public ViewHolder(final View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.row_name_id);

        }
    }


    // Pool row Info for onBindViewHolder
    public static class RowInfo {
        public String title;
    }

    // Updated Data set
    public void setData(List<String> stringList){
        mData = new ArrayList<>();
        for(String string : stringList){
            RowInfo rowInfo = new RowInfo();
            rowInfo.title = string;
            mData.add(rowInfo);
        }

    }


}
