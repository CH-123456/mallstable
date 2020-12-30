package com.example.mallstable.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallstable.R;

/*
 *created by liben 12.30
 */

public class ConfirmOrderActivity extends AppCompatActivity {
    private TextView name;
    private TextView mobile;
    private TextView addr_detail;

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

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

}
