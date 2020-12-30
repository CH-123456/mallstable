package com.example.mallstable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.example.mallstable.R;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.Product;

import java.util.List;

/**
 * created by ZangJie 12.30
 */
public class HomeHotProductAdapter extends DelegateAdapter.Adapter<HomeHotProductAdapter.HotProductViewHolder> {
    private List<Product> data;
    private Context context;
    private LayoutHelper layoutHelper;

    public HomeHotProductAdapter(List<Product> data, Context context, LayoutHelper layoutHelper) {
        this.data = data;
        this.context = context;
        this.layoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return this.layoutHelper;
    }

    @NonNull
    @Override
    public HotProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_hot_list_item,null,false);
        return new HotProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotProductViewHolder holder, int position) {
        if (position == 0){
            holder.titleContainer.setVisibility(View.VISIBLE);
        }else {
            holder.titleContainer.setVisibility(View.GONE);
        }
        Product product = data.get(position);
        holder.name.setText(product.getName());
        holder.price.setText("价格：¥"+product.getPrice());
        holder.stock.setText("库存："+product.getStock());
        holder.contentContainer.setTag(position);
        Glide.with(context).load(Constant.API.BASE_URL+product.getIconUrl()).into(holder.icon_url);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class HotProductViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout titleContainer;
        public RelativeLayout contentContainer;
        public TextView btn_more;
        public TextView name;
        public ImageView icon_url;
        public TextView stock;
        public TextView price;
        public HotProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titleContainer = (RelativeLayout)itemView.findViewById(R.id.title_container);
            contentContainer = (RelativeLayout)itemView.findViewById(R.id.content);
            name = (TextView)itemView.findViewById(R.id.name);
            stock = (TextView)itemView.findViewById(R.id.stock);
            price = (TextView)itemView.findViewById(R.id.price);
            icon_url = (ImageView)itemView.findViewById(R.id.icon_url);
            btn_more = (TextView)itemView.findViewById(R.id.btn_more);
        }
    }
}
