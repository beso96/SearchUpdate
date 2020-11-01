package com.sheel.searchupdate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DetailsFragment extends Fragment {

    private User mUser;

    public void setUser(User user) {
        mUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_details, container, false);
        EditText id = parentView.findViewById(R.id.id);
        EditText name = parentView.findViewById(R.id.name);
        EditText phone = parentView.findViewById(R.id.phone);
        Button delete = parentView.findViewById(R.id.delete);
        Button update = parentView.findViewById(R.id.update);
        Button add = parentView.findViewById(R.id.add);


        if (mUser != null) {
            id.setText(mUser.getId());
            name.setText(mUser.getName());
            phone.setText(mUser.getPhone());


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteUser();
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newName = name.getText().toString();
                    String newPhone = phone.getText().toString();

                    updateUser(newName, newPhone);
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newId = id.getText().toString();
                    String newName = name.getText().toString();
                    String newPhone = phone.getText().toString();

                    addUser(newId, newName, newPhone);
                }
            });

        }


        return parentView;
    }

    private void addUser(String id, String name, String phone) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setPhone(phone);

        myRef.child(u.getId()).setValue(u);
    }

    private void updateUser(String newName, String newPhone) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(mUser.getId());

        User u = mUser;
        u.setName(newName);
        u.setPhone(newPhone);

        myRef.setValue(u);
    }


    private void deleteUser() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(mUser.getId());
        myRef.removeValue();
        getActivity().onBackPressed();
    }


}