package com.example.mallstable.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mallstable.R;

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
    }


}
