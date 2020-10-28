package com.sheel.searchupdate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Updat_Search_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View parentView =  inflater.inflate(R.layout.fragment_updat__search_, container, false);
        EditText id = parentView.findViewById(R.id.id);
        EditText name = parentView.findViewById(R.id.name);
        EditText phone = parentView.findViewById(R.id.phone);
        EditText search = parentView.findViewById(R.id.search);
        FloatingActionButton fabsearch = parentView.findViewById(R.id.fabsearch);
        Button delete = parentView.findViewById(R.id.delete);
        Button update = parentView.findViewById(R.id.update);

        



        return parentView;
    }
}