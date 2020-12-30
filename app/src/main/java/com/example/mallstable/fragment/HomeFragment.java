package com.example.mallstable.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.mallstable.R;
import com.example.mallstable.pojo.Param;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * reated by ZangJie 12.30
 */

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRecylerView;
    private List<Integer> images;       //轮播图
    private List<Param> mCategoryData;  //产品类型参数

    private DelegateAdapter delegateAdapter;//定义代理适配器


    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        //获取RecyerView
        mRecylerView = (RecyclerView)view.findViewById(R.id.rv);
        //初始化轮播图
        images = new ArrayList<>();
        images.add(R.mipmap.lunbo01);
        images.add(R.mipmap.lunbo02);
        images.add(R.mipmap.lunbo03);
        //产品分类参数
        mCategoryData = new ArrayList<>();

        //初始化并绑定到RecyclerView
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        mRecylerView.setLayoutManager(layoutManager);
        //定义适配器列表
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        /*轮播图*/

        /*活动区*/

        /*热销商品*/

        delegateAdapter = new DelegateAdapter(layoutManager);
        delegateAdapter.setAdapters(adapters);
        mRecylerView.setAdapter(delegateAdapter);

    }

}
