package com.example.mallstable.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mallstable.R;
import com.example.mallstable.fragment.CartFragment;
import com.example.mallstable.fragment.CategoryFragment;
import com.example.mallstable.fragment.HomeFragment;
import com.example.mallstable.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;

    //定义tab对应的Fragment
    private Fragment homeFragment;
    private Fragment categoryFragment;
    private Fragment cartFragment;
    private Fragment userFragment;

    private RadioButton mRadioButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFagment();
    }

    private void initFagment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        homeFragment = new HomeFragment();
        ft.add(R.id.container,homeFragment,"home");
        categoryFragment = new CategoryFragment();
        ft.add(R.id.container,categoryFragment,"category");
        cartFragment = new CartFragment();
        ft.add(R.id.container,cartFragment,"cart");
        userFragment = new UserFragment();
        ft.add(R.id.container,userFragment,"user");
        ft.show(homeFragment).hide(categoryFragment).hide(cartFragment).hide(userFragment).commit();
    }
}
