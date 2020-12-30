package com.example.mallstable.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.mallstable.R;
import com.example.mallstable.adapter.HomeActAdapter;
import com.example.mallstable.adapter.HomeHotProductAdapter;
import com.example.mallstable.adapter.HomeTopBannerAndParamAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.pojo.Param;
import com.example.mallstable.pojo.Product;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.utils.JSONUtils;
import com.example.mallstable.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

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
    private List<Product> mProductData; //热销商品
    private final int PARAM_ROW_COL = 3;

    private HomeTopBannerAndParamAdapter homeTopBannerAndParamAdapter;
    private HomeHotProductAdapter homeHotProductAdapter;

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
        loadParams();
        loadHotProducts();
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
        mProductData = new ArrayList<>();

        //初始化并绑定到RecyclerView
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(getActivity());
        mRecylerView.setLayoutManager(layoutManager);
        //定义适配器列表
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        /*轮播图*/
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_banner,null,false);
        Banner banner = (Banner)headView.findViewById(R.id.banner);
        banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getScreenHeight(getActivity())/4));
        //设置表格布局
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(PARAM_ROW_COL);
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                if (position == 0){
                    return PARAM_ROW_COL;
                }else {
                    return 1;
                }
            }
        });
        homeTopBannerAndParamAdapter = new HomeTopBannerAndParamAdapter(mCategoryData,getActivity(),gridLayoutHelper);
        homeTopBannerAndParamAdapter.setHeadVioew(banner);
        adapters.add(homeTopBannerAndParamAdapter);

        /*活动区*/
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(Utils.dp2px(getActivity(),20));
        adapters.add(new HomeActAdapter(getActivity(),linearLayoutHelper));

        /*热销商品*/
        LinearLayoutHelper hotLayoutHelper = new LinearLayoutHelper();
        homeHotProductAdapter = new HomeHotProductAdapter(mProductData,getActivity(),hotLayoutHelper);
        adapters.add(homeHotProductAdapter);
        //点击热销商品，要跳转到详情页面




        delegateAdapter = new DelegateAdapter(layoutManager);
        delegateAdapter.setAdapters(adapters);
        mRecylerView.setAdapter(delegateAdapter);


        //启动轮播图
        banner.setImages(images);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }
        });
        banner.start();
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
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()){
                            if (result.getData()==null)
                                return;

                            if (result.getData().size()%PARAM_ROW_COL==0){
                                mCategoryData.addAll(result.getData());
                            }else {
                                int count = result.getData().size()/PARAM_ROW_COL;
                                mCategoryData.addAll(result.getData().subList(0,count*PARAM_ROW_COL));
                            }
                            homeTopBannerAndParamAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void loadHotProducts(){
        OkHttpUtils.get()
                .url(Constant.API.HOT_PRODUCT_URL)
                .addParams("num","10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Type type = new TypeToken<SverResponse<List<Product>>>() {
                        }.getType();
                        SverResponse<List<Product>> result = JSONUtils.fromJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() != null) {
                                mProductData.addAll(result.getData());
                            }
                        }
                    }
                });
    }
}