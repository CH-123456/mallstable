package com.example.mallstable.ui;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallstable.R;
import com.example.mallstable.adapter.AddressAdapter;
import com.example.mallstable.adapter.OrderAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.ActionOrderVo;
import com.example.mallstable.pojo.Address;
import com.example.mallstable.pojo.Order;
import com.example.mallstable.pojo.PageBean;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * created by zangjie 1.1
 */
public class OrderActivity extends AppCompatActivity {
    private List<ActionOrderVo> mData;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        initView();
        loadOrderList();//加载订单列表数据的方法
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.order_rv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("订单列表");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //应该加载订单详情页
            }
        });

        mData = new ArrayList<>();
        orderAdapter = new OrderAdapter(this, mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderAdapter);

        orderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                ActionOrderVo order = mData.get(pos);
                Intent intent = new Intent();
                intent.putExtra("order",order);
                setResult(RESULT_OK, intent);
                //销毁自己
                finish();
            }
        });

//        orderAdapter.setOnOrderOptListener(new OrderAdapter.OnOrderOptListener() {
//            @Override
//            public void deleteItem(View v, int pos) {
//                String id = mData.get(pos).getId() + "";
//                deleteOrder(id);
//            }
//        });
    }

    /**
     * 加载订单列表
     */
    private void loadOrderList() {
        /**
         * Created by wangquanli 2021/1/1
         */
//        List<Address>  result = new ArrayList<>();
//        for(int i=0;i<5;i++){
//            Address address = new Address();
//            address.setAddr("我是一个小可爱呀");
//            result.add(address);
//        }
//        mData.clear();
//        mData.addAll(result);
//        addressAdapter.notifyDataSetChanged();
        OkHttpUtils.get()
                .url(Constant.API.ORDER_LIST_URL)
                .addParams("status","0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(OrderActivity.this,"订单列表加载失败",Toast.LENGTH_LONG).show();
                        Log.e("订单列表加载",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("订单列表加载","订单列表加载成功");
                        Type type = new TypeToken<SverResponse<PageBean<ActionOrderVo>>>() {
                        }.getType();
                        SverResponse<PageBean<ActionOrderVo>> result = JSONUtils.fromJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            //mData.clear();
                            Toast.makeText(OrderActivity.this,"shuliang"+result.getData().getData().get(1).getStatus(),Toast.LENGTH_LONG).show();
                            mData.addAll(result.getData().getData());
                            orderAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    /**
     * 根据编号删除订单
     */
//    private void deleteOrder(String id) {
//        /*zhai*/
//
//        OkHttpUtils.get()
//                .url(Constant.API.USER_Order_DEL_URL)
//                .addParams("id", id)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Toast.makeText(OrderActivity.this,"删除订单失败",Toast.LENGTH_LONG).show();
//                        Log.e("删除订单",e.toString());
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.e("删除订单","删除订单成功");
//                        SverResponse result = JSONUtils.formJson(response, SverResponse.class);
//                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
//                            //重新加载数据
//                            loadOrderList();
//                        } else {
//                            Toast.makeText(OrderActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
}
