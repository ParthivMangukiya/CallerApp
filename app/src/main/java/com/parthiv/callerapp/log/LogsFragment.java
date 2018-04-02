package com.parthiv.callerapp.log;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parthiv.callerapp.R;

import java.util.ArrayList;


public class LogsFragment extends Fragment {

    RecyclerView logsRecycler;
    LogsViewModel mLogsViewModel;
    LogsAdapter mLogsAdapter;

    public LogsFragment() {
    }

    public static LogsFragment newInstance() {
        return new LogsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calllogs, container, false);
        mLogsAdapter = new LogsAdapter(getActivity(),new ArrayList<>());
        logsRecycler = rootView.findViewById(R.id.logssRecycler);
        logsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        logsRecycler.setItemAnimator(new DefaultItemAnimator());
        logsRecycler.setAdapter(mLogsAdapter);
        mLogsViewModel = ViewModelProviders.of(this).get(LogsViewModel.class);
        mLogsViewModel.getContacts().observe(this, mLogsAdapter::updateList);
        return rootView;
    }
}
