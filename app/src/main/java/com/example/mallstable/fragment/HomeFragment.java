package com.example.mallstable.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.Param;
import com.example.mallstable.pojo.Product;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.ui.DetailActivity;
import com.example.mallstable.utils.JSONUtils;
import com.example.mallstable.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;

/**
 * reated by ZangJie 12.30
 * modified by liben 12.31 add data
 * modified by liben 1.1 add function search
 * 商品按名称搜索的功能不知道好不好使（不知道接口是怎么传递参数的）
 * 而且首页的搜索框应该执行什么逻辑也不知道，是搜索全部商品还是搜索热销的商品
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
    private TextView search;

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

    private void initView(View view) {
        //获取RecyerView
        mRecylerView = (RecyclerView) view.findViewById(R.id.rv);
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
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_banner, null, false);
        Banner banner = (Banner) headView.findViewById(R.id.banner);
        banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getScreenHeight(getActivity()) / 4));
        //设置表格布局
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(PARAM_ROW_COL);
        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return PARAM_ROW_COL;
                } else {
                    return 1;
                }
            }
        });
        homeTopBannerAndParamAdapter = new HomeTopBannerAndParamAdapter(mCategoryData, getActivity(), gridLayoutHelper);
        homeTopBannerAndParamAdapter.setHeadVioew(banner);
        adapters.add(homeTopBannerAndParamAdapter);

        /*活动区*/
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(Utils.dp2px(getActivity(), 20));
        adapters.add(new HomeActAdapter(getActivity(), linearLayoutHelper));

        /*热销商品*/
        LinearLayoutHelper hotLayoutHelper = new LinearLayoutHelper();
        homeHotProductAdapter = new HomeHotProductAdapter(mProductData, getActivity(), hotLayoutHelper);
        homeHotProductAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //提取产品编号并跳转
                String id = mProductData.get(pos).getId() + "";
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
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

        //搜索功能
        search = view.findViewById(R.id.toolbar_searchview);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String name= String.valueOf(search.getText());
                    loadSearch(name);
                }
                return false;
            }
        });
    }

    private void loadParams() {
        //添加的数据（类别的）
//        List<Param> result = new ArrayList<Param>();
//        for (int i = 0; i < 9; i++) {
//            Param param = new Param(123, 456, "sfd", true, 33, 2, "", "");
//            result.add(param);
//        }
//        mCategoryData.addAll(result);
//        homeTopBannerAndParamAdapter.notifyDataSetChanged();
        //加载产品分类参数
        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PARAM_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(),"参数加载失败",Toast.LENGTH_LONG).show();
                        Log.e("参数加载",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("参数加载","参数加载成功");
                        final Type type = new TypeToken<SverResponse<List<Param>>>() {
                        }.getType();
                        SverResponse<List<Param>> result = JSONUtils.fromJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() == null)
                                return;

                            if (result.getData().size() % PARAM_ROW_COL == 0) {
                                mCategoryData.addAll(result.getData());
                            } else {
                                int count = result.getData().size() / PARAM_ROW_COL;
                                mCategoryData.addAll(result.getData().subList(0, count * PARAM_ROW_COL));
                            }
                            homeTopBannerAndParamAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void loadHotProducts() {
        //热销产品
//        List<Product> products = new ArrayList<Product>();
//        for (int i = 0; i < 9; i++) {
//            Product param = new Product();
//            param.setDetail("fdsfsd");
//            param.setHot(1);
//            param.setIconUrl("sdfsd");
//            param.setId(12);
//            param.setName("sfsdfsdfs");
//            param.setPrice(new BigDecimal(113));
//            param.setProductId(54);
//            param.setStatus(56);
//            param.setStock(56656);
//            products.add(param);
//        }
//        mProductData.addAll(products);
//        homeHotProductAdapter.notifyDataSetChanged();
        Log.e("热销产品加载","热销产品加载成功");
        OkHttpUtils.get()
                .url(Constant.API.HOT_PRODUCT_URL)
                .addParams("num", "10")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(),"热销产品加载失败",Toast.LENGTH_LONG).show();
                        Log.e("热销产品加载",e.toString());
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
                            homeHotProductAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void loadSearch(String name) {
//        List<Product> result1 = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            Product param = new Product();
//            param.setStock(111);
//            param.setStatus(2);
//            param.setProductId(13);
//            param.setPrice(new BigDecimal(1465));
//            param.setName("搜索的");
//            param.setId(6545);
//            param.setHot(2);
//            param.setDetail("搜索的");
//            param.setSpecParam("搜索的");
//            param.setPartsId(65465);
//            result1.add(param);
//        }
//        mProductData.clear();
//        mProductData.addAll(result1);
//        homeHotProductAdapter.notifyDataSetChanged();

        //HTTP 请求要添加的参数好像不对，具体修改
        OkHttpUtils.post()
                .url(Constant.API.SEARCH_PRODUCT_URL)
                .addParams("name",name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(),"搜索失败",Toast.LENGTH_LONG).show();
                        Log.e("搜索失败",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Type type = new TypeToken<SverResponse<List<Product>>>() {
                        }.getType();
                        SverResponse<List<Product>> result = JSONUtils.fromJson(response, type);
                        if (result.getStatus() == ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() != null) {
                                mProductData.clear();
                                mProductData.addAll(result.getData());
                            }
                            homeHotProductAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}