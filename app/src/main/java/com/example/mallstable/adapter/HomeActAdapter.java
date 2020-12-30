package com.example.mallstable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.example.mallstable.R;

/**
 * created by ZangJie 12.30 11:14
 */
public class HomeActAdapter extends DelegateAdapter.Adapter<HomeActAdapter.ActViewHolder> {
    private Context context;
    private LayoutHelper layoutHelper;

    public HomeActAdapter(Context context, LayoutHelper layoutHelper) {
        this.context = context;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.layoutHelper;
    }

    @NonNull
    @Override
    public ActViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_home_act_list_item,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    protected static class ActViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ActViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_act);
        }
    }
}
