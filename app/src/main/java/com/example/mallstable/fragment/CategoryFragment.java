package com.example.mallstable.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.mallstable.R;
import com.example.mallstable.adapter.CategoryLeftAdapter;
import com.example.mallstable.adapter.CategoryRightAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.PageBean;
import com.example.mallstable.pojo.Param;
import com.example.mallstable.pojo.Product;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.ui.DetailActivity;
import com.example.mallstable.utils.JSONUtils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
/*zhai*/
public class CategoryFragment extends Fragment {
 private RecyclerView leftRecyclerView;   //左侧列表组件
 private List<Param>   leftCategoryData;  //左侧分类数据

    private CategoryLeftAdapter categoryLeftAdapter;//分类适配器
    private RecyclerView rightRecyclerView;
    private List<Product>   rightProductData;
    private CategoryRightAdapter categoryRightAdapter;




    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        loadParams();

        return view;
    }


    private void initView(View view){
        //初始化
        leftRecyclerView=(RecyclerView)view.findViewById(R.id.category_rv);
        rightRecyclerView=(RecyclerView)view.findViewById(R.id.product_rv);


        leftCategoryData=new ArrayList<>();
        categoryLeftAdapter=new CategoryLeftAdapter(getActivity(),leftCategoryData);

    rightProductData=new ArrayList<>();
    categoryRightAdapter=new CategoryRightAdapter(getActivity(),rightProductData);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        leftRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        leftRecyclerView.setAdapter(categoryLeftAdapter);
        categoryLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String typeId=leftCategoryData.get(pos).getId()+"";
                findProductByParam(typeId,1,10,true);
            }
        });

        categoryRightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                String id = rightProductData.get(pos).getId() + "";
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        //网格布局管理器
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        rightRecyclerView.setLayoutManager(gridLayoutManager);
       rightRecyclerView.setAdapter(categoryRightAdapter);

    }
    private void loadParams(){
        //加载产品分类参数
        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PARAM_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Type type = new TypeToken<SverResponse<List<Param>>>(){}.getType();
                        SverResponse<List<Param>> result = JSONUtils.fromJson(response,type);
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() == null)
                                return;
                            leftCategoryData.addAll(result.getData());

                               String typeId=leftCategoryData.get(0).getId()+"";
                               leftCategoryData.get(0).setPressed(true);
                               findProductByParam(typeId,1,10,true);

                            categoryLeftAdapter.notifyDataSetChanged();

                        }
                    }
                });
    }
    private  void findProductByParam(String productTypeId, int pageNum, int pageSize, final boolean flag){
        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PRODUCT_URL)
                .addParams("productTypeId",productTypeId)
                .addParams("pageNum",pageNum+"")
                .addParams("pageSize",pageSize+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Type type = new TypeToken<SverResponse<PageBean<Product>>>(){}.getType();
                        SverResponse<PageBean<Product>> result = JSONUtils.fromJson(response,type);

                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() == null) {
                                if (flag){
                                    rightProductData.clear();
                                }
                                rightProductData.addAll(result.getData().getData());
                                categoryRightAdapter.notifyDataSetChanged();
                            }
                    }
                    }
                });

    }


}
