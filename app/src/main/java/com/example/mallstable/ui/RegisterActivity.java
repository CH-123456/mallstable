package com.example.mallstable.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

/**
 * 注册
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText accountEdit;         //用户名
    private EditText nameEdit;         //用户名
    private EditText phoneEdit;           //手机号码
    private EditText passwordEdit;        //密码
    private EditText re_passwordEdit;     //确认密码
    private EditText emailEdit;           //邮箱
    private EditText questionEdit;        //密码提示问题
    private EditText answerEdit;          //密码提示答案
    private Button registerButton;      //注册按钮

    private String account;
    private String name;
    private String phone ;
    private String password ;
    private String re_password;
    private String email ;
    private String question;
    private String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountEdit = (EditText) findViewById(R.id.accountEdit);
        nameEdit = (EditText) findViewById(R.id.name);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        re_passwordEdit = (EditText) findViewById(R.id.re_passwordEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        questionEdit = (EditText) findViewById(R.id.questionEdit);
        answerEdit = (EditText) findViewById(R.id.answerEdit);
        registerButton = (Button) findViewById(R.id.register);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 获取对应文本框中的内容 **/

                 account = accountEdit.getText().toString();
                name = nameEdit.getText().toString();
                 phone = phoneEdit.getText().toString();
                 password = passwordEdit.getText().toString();
                 re_password = re_passwordEdit.getText().toString();
                 email = emailEdit.getText().toString();
                 question = questionEdit.getText().toString();
                 answer = answerEdit.getText().toString();

//                Toast.makeText(RegisterActivity.this, "mm"+password, Toast.LENGTH_LONG).show();
//                Toast.makeText(RegisterActivity.this, "remm"+re_password, Toast.LENGTH_LONG).show();
//                Toast.makeText(RegisterActivity.this, "email:"+email, Toast.LENGTH_LONG).show();
                Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
                Matcher matcher = pattern.matcher(account);
                boolean b = matcher.matches();
                Pattern pattern2 = Pattern.compile("^[a-zA-Z0-9]+$");
                Matcher matcher2 = pattern2.matcher(password);
                boolean b2 = matcher2.matches();
                Pattern pattern3 = Pattern.compile("^1.*");
                Matcher matcher3 = pattern3.matcher(phone);
                boolean b3 = matcher3.matches();
                Pattern pattern4 = Pattern.compile("^[0-9]+$");
                Matcher matcher4 = pattern4.matcher(phone);
                boolean b4 = matcher4.matches();
                Pattern pattern5 = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE);
                Matcher matcher5 = pattern5.matcher(email);
                boolean b5 = matcher5.matches();
                /**规定用户名长度为3-16位，只能为英文和数字**/
                if (account.length() < 3 || account.length() > 16) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "用户名长度应在3-16位之间",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                if ("".equals(name)) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "用户名不为空",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                else if (b == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "用户名只能为英文和数字",
                            Toast.LENGTH_LONG);
                    tot.show();
                }

                /**规定密码长度为6-12位，只能为英文和数字**/
                else if (password.length() < 6 || password.length() > 12) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "密码长度应在6-12位之间",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                else if (b2 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "密码只能为英文和数字",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                /**判断两次输入的密码是否相同**/
                else if (!password.equals(re_password)) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "两次密码输入不同，请重新输入",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                /**规定手机号码长度为11位，开头数字为1，只能为数字**/
                else if (phone.length() != 11) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "请输入正确的手机号码",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                else if (b3 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "手机号开头为1",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                else if (b4 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "手机号只能为数字",
                            Toast.LENGTH_LONG);
                    tot.show();
                }

                /**规定邮箱格式**/
                else if (b5 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "请输入正确的邮箱",
                            Toast.LENGTH_LONG);
                    tot.show();
                }

                /** 规定所有的文本框不能为空**/
                else if (account == null && phone == null && password == null && re_password == null && email == null && question == null && answer == null) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "存在未输入信息，请重新填写。",
                            Toast.LENGTH_LONG);
                    tot.show();
                }else{
                    User user=new User();
                    user.setName(name);
                    user.setAccount(account);
                    user.setPhone(phone);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setQuestion(question);
                    user.setAsw(answer);
                    registerUser(user);
                }


            }
        });
    }
    private void registerUser(User user) {
        //添加用户
        Toast.makeText(RegisterActivity.this,"密码"+user.getPassword(),Toast.LENGTH_LONG).show();
        OkHttpUtils.post()
                .url(Constant.API.USER_REGISTER_URL)
                .addParams("user",JSONUtils.toJSON(user))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this,"用户添加失败",Toast.LENGTH_LONG).show();
                        Log.e("添加用户失败",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("添加用户加载","用户添加失败");
                        Type type=new TypeToken<SverResponse<User>>(){}.getType();
                        SverResponse<User> result= JSONUtils.formJson(response,type);
                        if(result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            Toast tot = Toast.makeText(RegisterActivity.this,result.getMsg(),Toast.LENGTH_LONG);
                            tot.show();
                            //跳转到登陆页面
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast tot = Toast.makeText(RegisterActivity.this,result.getMsg(),Toast.LENGTH_LONG);
                            tot.show();
                        }
                    }
                });
    }
}
