package com.example.mallstable.ui;
/**
 * created by bing 12.30
 * modified by bing 12.31
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mallstable.R;
import com.example.mallstable.adapter.AddressAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.Address;
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

public class AddressListActivity extends AppCompatActivity {

    private List<Address> mData;
    private AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initView();
        loadAddressList();//加载地址列表数据的方法
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.address_rv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("收货地址列表");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //应该加载地址详情页
            }
        });

        mData = new ArrayList<>();
        addressAdapter = new AddressAdapter(this, mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(addressAdapter);

        addressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Address address = mData.get(pos);
                Intent intent = new Intent();
                intent.putExtra("address", address);
                setResult(RESULT_OK, intent);
                //销毁自己
                finish();
            }
        });

        addressAdapter.setOnAddrOptListener(new AddressAdapter.OnAddrOptListener() {
            @Override
            public void deleteItem(View v, int pos) {
                String id = mData.get(pos).getId() + "";
                deleteAddress(id);
            }
        });
    }

    /**
     * 加载地址列表
     */
    private void loadAddressList() {
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
                .url(Constant.API.USER_ADDR_LIST_URL)
                .addParams("status","3")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddressListActivity.this,"地址列表加载失败",Toast.LENGTH_LONG).show();
                        Log.e("地址列表加载",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Toast.makeText(AddressListActivity.this,"地址列表加载成功",Toast.LENGTH_LONG).show();
                        Log.e("地址列表加载","地址列表加载成功");
                        Type type = new TypeToken<SverResponse<List<Address>>>() {
                        }.getType();
                        SverResponse<List<Address>> result = JSONUtils.fromJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            mData.clear();
                            mData.addAll(result.getData());
                            addressAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    /**
     * 根据编号删除地址
     */
    private void deleteAddress(String id) {
/*zhai*/

       OkHttpUtils.get()
                .url(Constant.API.USER_ADDR_DEL_URL)
                .addParams("id", id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddressListActivity.this,"删除地址失败",Toast.LENGTH_LONG).show();
                        Log.e("删除地址",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("删除地址","删除地址成功");
                        SverResponse result = JSONUtils.formJson(response, SverResponse.class);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            //重新加载数据
                            loadAddressList();
                        } else {
                            Toast.makeText(AddressListActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
