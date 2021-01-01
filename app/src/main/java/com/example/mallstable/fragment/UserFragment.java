package com.example.mallstable.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mallstable.R;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.pojo.User;
import com.example.mallstable.ui.LoginActivity;
import com.example.mallstable.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * create by liben12.29
 * modified by liben 12.31
 */
public class UserFragment extends Fragment {
    private TextView user;
    //本地广播
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        initUserInfo();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.LOAD_CART_ACTION);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                initUserInfo();
            }
        };
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initUserInfo();
        }
    }

    //加载UI
    private void initView(View view) {
        user = view.findViewById(R.id.user);
        view.findViewById(R.id.btn_addr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        view.findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initUserInfo() {
        //添加用户数据
        user.setText("sdfsf");

//        OkHttpUtils.get()
//                .url(Constant.API.USER_INFO_URL)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Type type=new TypeToken<SverResponse<User>>(){}.getType();
//                        SverResponse<User> result= JSONUtils.formJson(response,type);
//                        if(result.getStatus()== ResponeCode.SUCCESS.getCode()){
//                            user.setText(result.getData().getAccount());
//                        }
//                        else {
//                            //跳转到登陆页面
//                            Intent intent=new Intent(getActivity(), LoginActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                });
    }
}
