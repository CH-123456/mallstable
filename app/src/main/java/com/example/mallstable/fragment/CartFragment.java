package com.example.mallstable.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.mallstable.R;
import com.example.mallstable.adapter.CartAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.Cart;
import com.example.mallstable.pojo.CartItem;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.ui.LoginActivity;
import com.example.mallstable.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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

    private  boolean isEdit =false;
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
        loadCartData();
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
        // 12.30 li
        OkHttpUtils.get()
                .url(Constant.API.CART_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Cart>>(){}.getType();
                        SverResponse<Cart> result = JSONUtils.fromJson(response,type);
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            if (result.getData().getLists()!=null){
                                mData.clear();
                                mData.addAll(result.getData().getLists());
                                cartAdapter.notifyDataSetChanged();
                            }
                            total.setText("合计：￥"+result.getData().getTotalPrice());
                        }
                    }
                });
        //12.30
        /*
        if(result.getStatus()....)
        else{
        Intent intent=new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
       }
        * */
    }
    private  void updateProduct(int productId,int count){
        OkHttpUtils.get()
                .url(Constant.API.CART_UPDATE_URL)
                .addParams("productId",productId+"")
                .addParams("count",count+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Cart>>(){}.getType();
                        SverResponse<Cart> result = JSONUtils.fromJson(response,type);
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            if (result.getData().getLists()!=null){
                                mData.clear();
                                mData.addAll(result.getData().getLists());
                                cartAdapter.notifyDataSetChanged();
                            }
                            total.setText("合计：￥"+result.getData().getTotalPrice());
                        }

                    }
                });
    }
    /*删除商品 12.30 li*/
    private  void delProductById(int productId){
        OkHttpUtils.get()
                .url(Constant.API.CART_DEL_URL)
                .addParams("productId",productId+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Cart>>(){}.getType();
                        SverResponse<Cart> result = JSONUtils.fromJson(response,type);
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            if (result.getData().getLists()!=null){
                                mData.clear();
                                mData.addAll(result.getData().getLists());
                                cartAdapter.notifyDataSetChanged();
                            }
                            total.setText("合计：￥"+result.getData().getTotalPrice());
                        }
                    }
                });
    }
    //12.30
    public void initView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.cart_rv);
        total = (TextView)view.findViewById(R.id.total);
        btn_buy = (TextView)view.findViewById(R.id.buy_btn);

        mData = new ArrayList<>();
        cartAdapter = new CartAdapter(getActivity(),mData);
        LinearLayoutManager  linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.setOnCartOptListener(new CartAdapter.OnCartOptListener() {
            @Override
            public void updateProductCount(int productId, int count) {
                updateProduct(productId,count);

            }

            @Override
            public void delProductFromCart(int productId) {
                 delProductById(productId);
            }
        });


        view.findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit){
                    isEdit = false;
                    for (CartItem item:mData){
                        item.setEdit(true);
                    }

                }else {
                    isEdit = true;
                    for (CartItem item: mData){
                        item.setEdit(false);
                    }
                }

                cartAdapter.notifyDataSetChanged();

            }
        });

        //12.30
        cartAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转到详情页面
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到确定订单页
            }
        });
    }

}
