package com.example.mallstable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mallstable.config.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView)findViewById(R.id.img);
               final TextView textView = (TextView)findViewById(R.id.showText);
                Glide.with(MainActivity.this).load("https://img.alicdn.com/bao/uploaded/i1/1725562121/O1CN01FSJ3fK1RXShjlryLT_!!0-item_pic.jpg_468x468q75.jpg_.webp").into(imageView);

                //get()方法，然后使用哪种地址，build创建对象，execute执行。回调StringCallback
                OkHttpUtils.get().url(Constant.API.CATEGORY_PARAM).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        textView.setText(response);
                    }
                });
            }
        });
    }
}
