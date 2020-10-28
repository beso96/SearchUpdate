package com.sheel.searchupdate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;


public class RecycleViewFragment extends Fragment {
    private ArrayList<User> mUserInfo;
    private Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        mUserInfo = new ArrayList<>();

        RecyclerView recyclerView = parentView.findViewById(R.id.recyclerView);
        setupRecyclerView(recyclerView);


        mAdapter = new MyAdapter();
        createDemoData();

        mAdapter.setOnListItemClickListener(new MyAdapter.OnListItemClickedListener() {
            @Override
            public void onListItemClicked(User chat) {

            }
        });


        recyclerView.setAdapter(mAdapter);

        return parentView;

    }

    private void createDemoData() {
    }
    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        DividerItemDecoration decoration =
                new DividerItemDecoration(getContext(), layoutManager.getOrientation());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}