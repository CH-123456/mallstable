package com.example.mallstable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallstable.R;
/*import com.example.mallstable.TestActivity;*/
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.Param;

import java.util.List;

public class CategoryLeftAdapter extends RecyclerView.Adapter<CategoryLeftAdapter.CategroyViewHolder> implements  View.OnClickListener{
    private Context context;
    private List<Param> mData;
    private OnItemClickListener onItemClickListener;


    public CategoryLeftAdapter(Context context, List<Param> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CategroyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_category_left_list_item,null,false);
        return new CategroyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategroyViewHolder holder, int position) {
        Param param=mData.get(position);
        holder.name.setText(param.getName());
        holder.name.setTag(position);
        if (param.isPressed()){
            holder.name.setBackgroundResource(R.color.font_color);
        }else{
            holder.name.setBackgroundResource(R.color.colorWhite);
        }
  holder.name.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
  int pos=(int)v.getTag();
  for (int i=0;i<mData.size();i++){
      if (i==pos){
          mData.get(i).setPressed(true);
      }else{
          mData.get(i).setPressed(false);
      }
  }
  notifyDataSetChanged();
  if(onItemClickListener!=null){
      onItemClickListener.onItemClick(v,pos);
  }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  static class CategroyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public CategroyViewHolder(View itemView){
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
        }
    }


}
