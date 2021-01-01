package com.example.mallstable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mallstable.R;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.CartItem;

import java.util.List;

/*
 * created by liben 12.30
 */

public class ConfirmOrderProductAdapter extends RecyclerView.Adapter<ConfirmOrderProductAdapter.ConfirmOrderViewHolder> {
    private Context context;
    private List<CartItem> mData;

    public ConfirmOrderProductAdapter(Context context,List<CartItem> mData) {
        this.context=context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ConfirmOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.confirm_order_list_item,null,false);
        return new ConfirmOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderViewHolder holder, int position) {
        CartItem item=mData.get(position);
        holder.name.setText(item.getName());
        holder.name.setText(item.getPrice()+"");
        holder.name.setText(item.getQuantity()+"");
        Glide.with(context).load(Constant.API.BASE_URL+item.getIconUrl()).into(holder.icon_url);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ConfirmOrderViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public ImageView icon_url;
        public TextView price;
        public TextView name;
        public TextView num;

        public ConfirmOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            icon_url=itemView.findViewById(R.id.icon_url);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            num=itemView.findViewById(R.id.num);
        }
    }
}
