package com.example.mallstable.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mallstable.R;

/**
 * A simple {@link Fragment} subclass.
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
        return view;
    }
    //加载UI
    private void initView(View view){
        user=view.findViewById(R.id.user);
        view
        view.findViewById(R.id);
        view.findViewById(R.id);
        view.findViewById(R.id);
        view.findViewById(R.id);
        view.findViewById(R.id);
        view.findViewById(R.id);
        view.findViewById(R.id);

    }
}
