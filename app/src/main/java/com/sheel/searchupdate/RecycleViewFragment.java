package com.sheel.searchupdate;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RecycleViewFragment extends Fragment {
    private ArrayList<User> mUserInfo;
    private MyAdapter mAdapter;
    private MediatorInterface mMediatorCallback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mMediatorCallback = (MediatorInterface) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_recycler, container, false);
        mUserInfo = new ArrayList<>();

        RecyclerView recyclerView = parentView.findViewById(R.id.recycler_view);
        setupRecyclerView(recyclerView);


        mAdapter = new MyAdapter();


        mAdapter.setOnListItemClickListener(new MyAdapter.OnListItemClickedListener() {
            @Override
            public void onListItemClicked(User user) {

                DetailsFragment fragment = new DetailsFragment();
                fragment.setUser(user);
                mMediatorCallback.changeFragmentTo(fragment, DetailsFragment.class.getSimpleName());

            }
        });


        recyclerView.setAdapter(mAdapter);
        ReadCurrentUserData();


        EditText etSearch = parentView.findViewById(R.id.etSearch);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() > 0) {
                    search(charSequence.toString());
                } else {
                    mAdapter.update(mUserInfo);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return parentView;

    }

    private void ReadCurrentUserData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(MyConstants.FB_KEY_USERS);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUserInfo.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    User value = d.getValue(User.class);
                    mUserInfo.add(value);
                }

                mAdapter.update(mUserInfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void search(String key) {

        ArrayList<User> temp = new ArrayList<>();

        for (User u : mUserInfo) {

            boolean isNameMatched = u.getName().toLowerCase().contains(key.toLowerCase());
            boolean isPhoneMatched = u.getPhone().toLowerCase().contains(key.toLowerCase());
            if (isNameMatched || isPhoneMatched) {
                temp.add(u);
            }
        }

        mAdapter.update(temp);
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