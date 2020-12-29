package com.example.mallstable.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mallstable.R;

/**注册**/
public class RegisterActivity extends AppCompatActivity{

    private EditText accountEdit;         //用户名
    private EditText phoneEdit;           //手机号码
    private EditText passwordEdit;        //密码
    private EditText re_passwordEdit;     //确认密码
    private EditText emailEdit;           //邮箱
    private EditText questionEdit;        //密码提示问题
    private EditText answerEdit;          //密码提示答案
    private Button   registerButton;      //注册按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountEdit = (EditText)findViewById(R.id.accountEdit);
        phoneEdit = (EditText)findViewById(R.id.phoneEdit);
        passwordEdit = (EditText)findViewById(R.id.passwordEdit);
        re_passwordEdit = (EditText)findViewById(R.id.re_passwordEdit);
        emailEdit = (EditText)findViewById(R.id.emailEdit);
        questionEdit = (EditText)findViewById(R.id.questionEdit);
        answerEdit = (EditText)findViewById(R.id.answerEdit);
        registerButton = (Button)findViewById(R.id.register);

        /** 获取对应文本框中的内容 **/
        final String account = accountEdit.getText().toString();
        final String phone = accountEdit.getText().toString();
        final String password = accountEdit.getText().toString();
        final String re_password= accountEdit.getText().toString();
        final String email = accountEdit.getText().toString();
        final String question = accountEdit.getText().toString();
        final String answer = accountEdit.getText().toString();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 规定所有的文本框不能为空**/
                if (account==null&&phone==null&&password==null&&re_password==null&&email==null&&question==null&&answer==null){
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "存在未输入信息，请重新填写。",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
                /**规定用户名长度为3-16位，只能为英文和数字**/

                /**规定密码长度为6-12位，只能为英文和数字**/

                /**规定手机号码长度为11位，开头数字为1，只能为数字**/

                /**规定邮箱格式**/


                /**判断两次输入的密码是否相同**/
                if(password!=re_password){
                    Toast tot = Toast.makeText(
                            RegisterActivity.this,
                            "两次密码输入不同，请重新输入",
                            Toast.LENGTH_LONG);
                    tot.show();
                }
            }
        });

    }
}
