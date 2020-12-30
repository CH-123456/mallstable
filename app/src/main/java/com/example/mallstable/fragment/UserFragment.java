package com.example.mallstable.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mallstable.R;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.pojo.User;
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
 */
public class UserFragment extends Fragment {
    private TextView user;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        initUserInfo();
        return view;
    }
    //加载UI
    private void initView(View view){
        user=view.findViewById(R.id.user);
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

    private  void initUserInfo(){
        OkHttpUtils.get()
                .url(Constant.API.USER_INFO_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Type type=new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result= JSONUtils.formJson(response,type);
                        if(result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            user.setText(result.getData().getAccount());
                        }
                        else {
                            //跳转到登陆页面
                        }
                    }
                });
    }
}
