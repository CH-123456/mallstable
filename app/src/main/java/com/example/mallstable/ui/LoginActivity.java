package com.example.mallstable.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mallstable.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText accountEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_register).setOnClickListener(this);



    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:

                break;
            case R.id.btn_register:
                break;

        }
    }
    private  void Login(){
        String account=accountEdit.getText().toString();
        String password=accountEdit.getText().toString()
                if(TextUtils.isEmpty(account)){
                    Toast.makeText(this,"请输入登录账号！",Toast.LENGTH_LONG).show();
                    return;
                }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"请输入登录密码！",Toast.LENGTH_LONG).show();
            return;
        }
        OkHttpUtils.post()
        .url(Constant.API.USER_LOGIN_URL)
        .addParams("account",account)
        .addParams("paasword",password)
        .build()
        .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(LoginActivity.this,"网络问题，请稍后重试！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Type type=new TypeToken<SverResponse<User>>(){}.getType();
                /*TypeToken 导包*/
                SverResponse<User> result=JSONUtils.fromJson(response.type);
                if(result.getStatus()==ResponeCode.SUCESS.getCode()){

                }else{
                    Toast.makeText(LoginActivity.this,result.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






}
