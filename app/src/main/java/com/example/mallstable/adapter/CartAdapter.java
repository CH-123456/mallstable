package com.example.mallstable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.mallstable.R;
import com.example.mallstable.config.Constant;
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.CartItem;
import java.util.List;

/***
 * li 12.30
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
        implements View.OnClickListener {
    private Context context;//上下文
    private List<CartItem> mData;
    private OnItemClickListener onItemClickListener;
    private OnCartOptListener onCartOptListener;

    public CartAdapter(Context context, List<CartItem> mData) {
        this.context = context;
        this.mData = mData;
    }

    // @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //生成一个viewholder
        //加载视图
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_cart_list_item,null,false);
        return new CartViewHolder(view);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setOnCartOptListener(OnCartOptListener onCartOptListener) {
        this.onCartOptListener = onCartOptListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
         final CartItem cartItem = mData.get(position);
         holder.name.setText(cartItem.getName());
         holder.price.setText(cartItem.getPrice()+"");
         holder.edit_num.setText(cartItem.getQuantity()+"");
        Glide.with(context).load(Constant.API.BASE_URL+cartItem.getIconUrl()).into(holder.icon_url);
        //绑定点击item的监听
        //整个条目的监听器
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);

        //增加按钮
        holder.btn_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItem.getQuantity()+1<=cartItem.getStock()){
                    if(onCartOptListener!=null){
                        onCartOptListener.updateProductCount(cartItem.getProductId(),cartItem.getQuantity()+1);
                    }
                }
            }
        });
        //减,,,数量上的更新
        holder.btn_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItem.getQuantity()-1>=1){
                    if (onCartOptListener!=null){
                        onCartOptListener.updateProductCount(cartItem.getProductId(),cartItem.getQuantity()-1);
                    }
                }
            }
        });

        if (cartItem.isEdit()){
            holder.btn_del.setVisibility(View.VISIBLE);
        }else {
            holder.btn_del.setVisibility(View.GONE);
        }

       holder.btn_del.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (onCartOptListener!=null){
                   onCartOptListener.delProductFromCart(cartItem.getProductId());
               }
           }
       });
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder{
        //定义相关控件
        private View itemView;
        public ImageView icon_url;
        public TextView name;
        public TextView price;
        public TextView btn_jian;
        public EditText edit_num;
        public TextView btn_jia;
        public TextView btn_del;

       public CartViewHolder(View itemView){
           super(itemView);
           this.itemView = itemView;
           name =(TextView)itemView.findViewById(R.id.name);
           price = (TextView)itemView.findViewById(R.id.price);
           btn_del = (TextView)itemView.findViewById(R.id.btn_del);
           btn_jian = (TextView)itemView.findViewById(R.id.btn_jian);
           btn_jia = (TextView)itemView.findViewById(R.id.btn_jia);
           edit_num = (EditText) itemView.findViewById(R.id.edit_num);
           icon_url = (ImageView)itemView.findViewById(R.id.icon_url);


       }
   }

    public interface OnCartOptListener{
        //更新商品数量
        public void updateProductCount(int productId,int count);
        //删除商品
        public void delProductFromCart(int productId);
    }
}
