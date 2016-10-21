package com.wesgue.loaderproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by Wesley Gue on 10/20/2016.
 */

public class RowListFragment extends Fragment {

    // Member Variables
    private View mLayout;
    private RecyclerView mRecyclerView;
    private LoaderAdapter mAdapter;
    private LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
    private StringLoader loader;


    public RowListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = (StringLoader) getActivity().getSupportLoaderManager().initLoader(R.id.string_loader_id, null, loaderCallbacks);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mLayout = inflater.inflate(R.layout.fragment_row_list, container, false);
        //set up Recycler View for Pools
        mRecyclerView = (RecyclerView) mLayout.findViewById(R.id.recycler_view_id);

        // set PoolRowAdapter
        setAdapterList();

        mLayout.findViewById(R.id.button_force_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loader != null)
                    loader.loadNewStrings();
            }
        });


        return mLayout;
    }


    // Set PoolRowAdapter
    public void setAdapterList() {

        mAdapter = new LoaderAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemViewCacheSize(0); // should not dupe with this setting
    }

    private LoaderManager.LoaderCallbacks<List<String>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<String>>() {

        @Override
        public Loader<List<String>> onCreateLoader(int id, Bundle args) {
            return new StringLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<List<String>> loader, List<String> strings) {

            // set data
            mAdapter.setData(strings);
            mAdapter.notifyDataSetChanged();

        }

        @Override
        public void onLoaderReset(Loader<List<String>> loader) {

            mAdapter.setData(Collections.<String>emptyList());
            mAdapter.notifyDataSetChanged();
            // loader has been destroyed
            loader = null;

        }
    };

}
