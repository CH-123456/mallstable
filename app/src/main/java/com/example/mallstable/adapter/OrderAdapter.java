package com.example.mallstable.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallstable.R;
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.ActionOrderVo;
import com.example.mallstable.pojo.Address;
import com.example.mallstable.pojo.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private List<ActionOrderVo> mData;
    private OnItemClickListener onItemClickListener;
    //private OrderAdapter.OnOrderOptListener onOrderOptListener;

    public OrderAdapter(Context context, List<ActionOrderVo> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_order_list_item, null, false);
        return new OrderAdapter.OrderViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    public void setOnOrderOptListener(OrderAdapter.OnOrderOptListener onOrderOptListener) {
//        this.onOrderOptListener = onOrderOptListener;
//    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, final int position) {
        ActionOrderVo order = mData.get(position);
        holder.orderNo.setText("订单编号："+String.valueOf(order.getOrderNo()) );
        String status=" ";
        Log.e("状态",order.getStatus().getClass().toString());
        Log.e("状态",order.getStatus().toString());
        switch ((int)order.getStatus()){
            case 1:status="未付款";break;
            case 2:status="已付款";break;
            case 3:status="已发货";break;
            case 4:status="交易成功";break;
            case 5:status="交易关闭";break;
            case 6:status="已取消";break;
        }
        holder.orderStatus.setText("订单状态："+status);
        StringBuffer sb = new StringBuffer();
//        if (!TextUtils.isEmpty(order.getProvince())) {
//            sb.append(order.getProvince()).append(" ");
//        }
//        if (!TextUtils.isEmpty(order.getCity())) {
//            sb.append(order.getCity()).append(" ");
//        }
//        if (!TextUtils.isEmpty(order.getDistrict())) {
//            sb.append(order.getDistrict()).append(" ");//        }
//        if (!TextUtils.isEmpty(order.getAddr())) {
//            sb.append(order.getAddr()).append(" ");
//        }
        holder.order_detail.setText("订单金额："+String.valueOf(order.getAmount()));
//        holder.bt_del.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (onOrderOptListener != null) {
//                    onOrderOptListener.deleteItem(view, position);
//                }
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView orderNo;
        public TextView orderStatus;
        public TextView order_detail;
        //public TextView bt_del;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            orderNo = (TextView) itemView.findViewById(R.id.orderNo);
            orderStatus = (TextView) itemView.findViewById(R.id.orderStatus);
            order_detail = (TextView) itemView.findViewById(R.id.order_detail);
            //bt_del = (TextView) itemView.findViewById(R.id.btn_del);
        }
    }

//    public interface OnOrderOptListener {
//        //删除
//        public void deleteItem(View v, int pos);
//    }
}
