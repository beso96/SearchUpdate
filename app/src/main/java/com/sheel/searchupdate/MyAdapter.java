package com.sheel.searchupdate;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<User> mUserInfo;
    private OnListItemClickedListener mListener;

    public MyAdapter() {
        mUserInfo = new ArrayList<>();
    }

    public void update(ArrayList<User> items) {
        mUserInfo = items;
        notifyDataSetChanged();
    }


    public void update(User newItem) {
        mUserInfo.add(newItem);
        notifyItemInserted(mUserInfo.indexOf(newItem));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_service_provider, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final User userInfo = mUserInfo.get(position);
        holder.id.setText(userInfo.getId());
        holder.name.setText(userInfo.getName());
        holder.phone.setText(userInfo.getPhone());


    }

    @Override
    public int getItemCount() {
        return mUserInfo.size();
    }

    public void setOnListItemClickListener(OnListItemClickedListener listener) {
        mListener = listener;
    }

    public interface OnListItemClickedListener {
        void onListItemClicked(User user);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView id;
        TextView phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            phone = itemView.findViewById(R.id.phone);


        }
    }
}
