package com.example.mallstable.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mallstable.R;
import com.example.mallstable.adapter.ConfirmOrderProductAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.Address;
import com.example.mallstable.pojo.Cart;
import com.example.mallstable.pojo.CartItem;
import com.example.mallstable.pojo.Order;
import com.example.mallstable.pojo.Param;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.pojo.User;
import com.example.mallstable.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/*
 *created by liben 12.30
 * modified by bing 12.31 10:24
 */

public class ConfirmOrderActivity extends AppCompatActivity {
    private TextView name;
    private TextView mobile;
    private TextView addr_detail;
    private TextView total;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private ConfirmOrderProductAdapter confirmOrderProductAdapter;
    private List<CartItem> cartItems;

    private Address defaultAddr;

    private static final int REQ_ADDR_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();
        initDefaultAdr();
        initCartProducts();
    }

    private void initView() {
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        addr_detail = findViewById(R.id.addr_detail);
        recyclerView = findViewById(R.id.cart_rv);
        total = findViewById(R.id.total);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("确认订单信息");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cartItems = new ArrayList<>();
        confirmOrderProductAdapter = new ConfirmOrderProductAdapter(this, cartItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(confirmOrderProductAdapter);

        //提交订单
        findViewById(R.id.buy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();
            }
        });

        //选择地址
        findViewById(R.id.address_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmOrderActivity.this, AddressListActivity.class);
                startActivityForResult(intent, REQ_ADDR_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQ_ADDR_CODE) {
            if (resultCode == RESULT_OK) {
                defaultAddr = (Address) data.getSerializableExtra("address");
                displayInfo();
            }
        }
    }

    /**
     * 显示地址信息
     */
    private void displayInfo() {
        name.setText(defaultAddr.getName());
        mobile.setText(defaultAddr.getMobile());
        addr_detail.setText(defaultAddr.getProvince() + " " + defaultAddr.getCity()
                + " " + defaultAddr.getDistrict() + " " + defaultAddr.getAddr());
    }

    /**
     * 加载默认地址
     */
    private void initDefaultAdr() {
        /*zhai*/
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Address param = new Address();
            param.setAddr("address");
            param.setCity("city");
            param.setCreated(null);
            param.setDel(false);
            param.setDfault(true);
            param.setDistrict("district");
            param.setId(12);
            param.setMobile("123456");
            param.setName("CH");
            param.setPhone("123456");
            param.setProvince("province");
            param.setUid(13);
            param.setUpdated(null);
            param.setZip("123");
            addresses.add(param);
        }
      /*OkHttpUtils.get()
                .url(Constant.API.USER_ADDR_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<List<Address>>>() {
                        }.getType();
                        SverResponse<List<Address>> result = JSONUtils.formJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() != null) {
                                //迭代器找到默认地址
                                for (Address adr : result.getData()) {
                                    if (adr.isDfault()) {
                                        defaultAddr = adr;
                                        break;
                                    }
                                }
                                //设置默认值
                                if (defaultAddr == null) {
                                    defaultAddr = result.getData().get(0);
                                }
                                displayInfo();
                            } else {
                                name.setText("");
                                mobile.setText("");
                                addr_detail.setText("请选择收件地址");
                            }
                        } else {
                            name.setText("");
                            mobile.setText("");
                            addr_detail.setText("请选择收件地址");
                        }
                    }
                });*/
    }

    /*
     * 加载购物车数据
     */
    private void initCartProducts() {
        /*zhai*/
        List<CartItem> lists = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            CartItem param = new CartItem();
            param.setEdit(true);
            param.setIconUrl("初始加载的2");
            param.setId(322);
            param.setName("初始加载的2");
            param.setPrice(new BigDecimal(56462));
            param.setProductId(6542);
            param.setStatus(522);
            param.setStock(322);
            param.setUserId(62);
            param.setTotalPrice(new BigDecimal(987988989));
            param.setQuantity(32);
            lists.add(param);
        }
        cartItems.addAll(lists);
        confirmOrderProductAdapter.notifyDataSetChanged();
        /*OkHttpUtils.get()
                .url(Constant.API.CART_LIST_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Cart>>() {
                        }.getType();
                        SverResponse<Cart> result = JSONUtils.formJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            cartItems.clear();
                            cartItems.addAll(result.getData().getLists());
                            confirmOrderProductAdapter.notifyDataSetChanged();
                        }
                        total.setText("合计：" + result.getData().getTotalPrice());
                    }
                });*/

    }

    private void submitOrder() {
        if (defaultAddr == null) {
            Toast.makeText(this, "请选择收货地址！", Toast.LENGTH_LONG).show();
            return;
        }
        /*
        OkHttpUtils.post()
                .url(Constant.API.ORDER_CREATED_URL)
                .addParams("addrId", defaultAddr.getId() + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type = new TypeToken<SverResponse<Order>>() {
                        }.getType();
                        SverResponse<Order> result = JSONUtils.formJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            //跳转到订单详情
                        } else {
                            Toast.makeText(ConfirmOrderActivity.this, result.getStatus(), Toast.LENGTH_LONG).show();
                        }
                    }
                });*/

    }
}
