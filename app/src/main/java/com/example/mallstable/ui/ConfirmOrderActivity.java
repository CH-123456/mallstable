package com.example.mallstable.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallstable.R;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.Address;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.pojo.User;
import com.example.mallstable.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;

/*
 *created by liben 12.30
 */

public class ConfirmOrderActivity extends AppCompatActivity {
    private TextView name;
    private TextView mobile;
    private TextView addr_detail;

    private RecyclerView recyclerView;

    private Address defaultAddr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();
        initDefaultAdr();
    }
    private void initView(){
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        addr_detail=findViewById(R.id.addr_detail);
        recyclerView=findViewById(R.id.cart_rv);

        //提交订单
        findViewById(R.id.buy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //选择地址
        findViewById(R.id.address_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //加载地址
    private void initDefaultAdr(){
        OkHttpUtils.get()
                .url(Constant.API.USER_ADDR_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type=new TypeToken<SverResponse<List<Address>>>(){}.getType();
                        SverResponse<List<Address>> result= JSONUtils.formJson(response,type);
                        if(result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            if(result.getData()!=null) {
                                //迭代器找到默认地址
                                for (Address adr : result.getData()){
                                    if(adr.isDfault()){
                                        defaultAddr=adr;
                                        break;
                                    }
                                }
                                //设置默认值
                                if(defaultAddr==null){
                                    defaultAddr=result.getData().get(0);
                                }
                                name.setText(defaultAddr.getName());
                                mobile.setText(defaultAddr.getMobile());
                                addr_detail.setText(defaultAddr.getProvince()+" "+defaultAddr.getCity()
                                        +" "+defaultAddr.getDistrict()+" "+defaultAddr.getAddr());
                            }else{
                                name.setText("");
                                mobile.setText("");
                                addr_detail.setText("请选择收件地址");
                            }
                        }
                        else {
                            name.setText("");
                            mobile.setText("");
                            addr_detail.setText("请选择收件地址");
                        }
                    }
                });
    }
}
