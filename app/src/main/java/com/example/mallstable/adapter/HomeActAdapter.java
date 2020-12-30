package com.example.mallstable.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * created by ZangJie 12.30 11:14
 */
public class HomeActAdapter extends DelegateAdapter.Adapter<HomeActAdapter.ActViewHolder> {

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return null;
    }

    @NonNull
    @Override
    public ActViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ActViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected static class ActViewHolder extends RecyclerView.ViewHolder{
        public ActViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
