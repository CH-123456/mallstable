package com.example.mallstable.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mallstable.R;
import com.example.mallstable.adapter.CartAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.CartItem;
import com.example.mallstable.ui.LoginActivity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    // li 12,30
    private RecyclerView recyclerView;
    private List<CartItem> mData;
    private CartAdapter cartAdapter;
    //要显示的总价格
    private TextView total;
    private TextView btn_buy;
    //上bian


    //本地广播
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        initView(view);
        return  view;
    }



    /*登录注册广播修改    CH*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //注册广播
        localBroadcastManager=LocalBroadcastManager.getInstance(getActivity());
        intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.ACTION.LOAD_CART_ACTION);
        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //加载购物车数据
            }
        };
        /*注册*/
        localBroadcastManager.registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        /*注销*/
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            loadCartData();
        }
    }
/*加载购物车数据*/
    private void loadCartData() {

        /*
        if(result.getStatus()....)
        else{
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
       }
        * */
    }

    public void initView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.cart_rv);
    }

}
