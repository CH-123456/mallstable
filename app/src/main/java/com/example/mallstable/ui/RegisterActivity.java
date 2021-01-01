package com.example.mallstable.ui;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mallstable.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText accountEdit;         //用户名
    private EditText phoneEdit;           //手机号码
    private EditText passwordEdit;        //密码
    private EditText re_passwordEdit;     //确认密码
    private EditText emailEdit;           //邮箱
    private EditText questionEdit;        //密码提示问题
    private EditText answerEdit;          //密码提示答案
    private Button registerButton;      //注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountEdit = (EditText) findViewById(R.id.accountEdit);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        re_passwordEdit = (EditText) findViewById(R.id.re_passwordEdit);
        emailEdit = (EditText) findViewById(R.id.emailEdit);
        questionEdit = (EditText) findViewById(R.id.questionEdit);
        answerEdit = (EditText) findViewById(R.id.answerEdit);
        registerButton = (Button) findViewById(R.id.register);

        /** 获取对应文本框中的内容 **/
        final String account = accountEdit.getText().toString();
        final String phone = phoneEdit.getText().toString();
        final String password = passwordEdit.getText().toString();
        final String re_password = re_passwordEdit.getText().toString();
        final String email = emailEdit.getText().toString();
        final String question = questionEdit.getText().toString();
        final String answer = answerEdit.getText().toString();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 规定所有的文本框不能为空**/
                if (account == null && phone == null && password == null && re_password == null && email == null && question == null && answer == null) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "存在未输入信息，请重新填写。",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                /**规定用户名长度为3-16位，只能为英文和数字**/
                if (account.length() < 3 || account.length() > 16) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "用户名长度应在3-16位之间",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
                Matcher matcher = pattern.matcher(account);
                boolean b = matcher.matches();
                if (b == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "用户名只能为英文和数字",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
/*
参考代码：
                Pattern pattern = Pattern.compile("^Java.*");
                Matcher matcher = pattern.matcher("Java不是人");
                boolean b= matcher.matches();
                //当条件满足时，将返回true，否则返回false
                String content = "要匹配的字符串";
                Pattern p = Pattern.compile("正则表达式");
                Matcher m = p.matcher(content);
*/
                /**规定密码长度为6-12位，只能为英文和数字**/
                if (password.length() < 6 || password.length() > 12) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "密码长度应在6-12位之间",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                Pattern pattern2 = Pattern.compile("^[a-zA-Z0-9]+$");
                Matcher matcher2 = pattern2.matcher(password);
                boolean b2 = matcher2.matches();
                if (b2 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "密码只能为英文和数字",
                            Toast.LENGTH_LONG);
                    tot.show();
                }

                /**规定手机号码长度为11位，开头数字为1，只能为数字**/
                if (phone.length() != 11) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "请输入正确的手机号码",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                Pattern pattern3 = Pattern.compile("^1.*");
                Matcher matcher3 = pattern3.matcher(phone);
                boolean b3 = matcher3.matches();
                if (b3 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "手机号开头为1",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                Pattern pattern4 = Pattern.compile("^[0-9]+$");
                Matcher matcher4 = pattern4.matcher(phone);
                boolean b4 = matcher4.matches();
                if (b4 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "手机号只能为数字",
                            Toast.LENGTH_LONG);
                    tot.show();
                }

                /**规定邮箱格式**/

                Pattern pattern5 = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE);
                Matcher matcher5 = pattern5.matcher(email);
                boolean b5 = matcher5.matches();
                if (b5 == false) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "请输入正确的邮箱",
                            Toast.LENGTH_LONG);
                    tot.show();
                }


                /**判断两次输入的密码是否相同**/
                if (password != re_password) {
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "两次密码输入不同，请重新输入",
                            Toast.LENGTH_LONG);
                    tot.show();
                }

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
