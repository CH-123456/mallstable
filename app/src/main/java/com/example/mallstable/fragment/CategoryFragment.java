package com.example.mallstable.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.mallstable.R;
import com.example.mallstable.adapter.CategoryLeftAdapter;
import com.example.mallstable.adapter.CategoryRightAdapter;
import com.example.mallstable.config.Constant;
import com.example.mallstable.listener.OnItemClickListener;
import com.example.mallstable.pojo.CartItem;
import com.example.mallstable.pojo.PageBean;
import com.example.mallstable.pojo.Param;
import com.example.mallstable.pojo.Product;
import com.example.mallstable.pojo.ResponeCode;
import com.example.mallstable.pojo.SverResponse;
import com.example.mallstable.ui.DetailActivity;
import com.example.mallstable.utils.JSONUtils;
import com.example.mallstable.utils.SpaceItemDecoration;
import com.example.mallstable.utils.Utils;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 * modified by liben 12.31
 */
/*zhai*/
public class CategoryFragment extends Fragment {
    private RecyclerView leftRecyclerView;   //左侧列表组件
    private List<Param> leftCategoryData;  //左侧分类数据

    private CategoryLeftAdapter categoryLeftAdapter;//分类适配器
    private RecyclerView rightRecyclerView;
    private List<Product> rightProductData;
    private CategoryRightAdapter categoryRightAdapter;
    private MaterialRefreshLayout refreshLayout;
    private SverResponse<List<Product>> result;
    private String typeId;
    private TextView search;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        loadParams();
        return view;
    }


    private void initView(View view) {
        //初始化
        leftRecyclerView = (RecyclerView) view.findViewById(R.id.category_rv);
        rightRecyclerView = (RecyclerView) view.findViewById(R.id.product_rv);
        refreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh_layout);

        leftCategoryData = new ArrayList<>();
        categoryLeftAdapter = new CategoryLeftAdapter(getActivity(), leftCategoryData);
        rightProductData = new ArrayList<>();
        categoryRightAdapter = new CategoryRightAdapter(getActivity(), rightProductData);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        leftRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        leftRecyclerView.setAdapter(categoryLeftAdapter);
        categoryLeftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                typeId = leftCategoryData.get(pos).getId() + "";
                Toast.makeText(getActivity(),"类型"+typeId,Toast.LENGTH_LONG).show();
                findProductByParam(typeId, 1, 10, true);
            }
        });

        categoryRightAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                //跳转到详情页面
                //提取产品编号并跳转
                //谁删谁是Big SB
                String id = rightProductData.get(pos).getId() + "";
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        //网格布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rightRecyclerView.addItemDecoration(new SpaceItemDecoration(Utils.dp2px(getActivity(), 10), Utils.dp2px(getActivity(), 5)));
        rightRecyclerView.setLayoutManager(gridLayoutManager);
        rightRecyclerView.setAdapter(categoryRightAdapter);

        //搜索功能
        search = view.findViewById(R.id.toolbar_searchview);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    loadSearch();
                }
                return false;
            }
        });
    }

    private void bindRefreshLinstener() {
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新
                refreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //super.onRefreshLoadMore(materialRefreshLayout);
//                if (result != null && result.getStatus() == ResponeCode.SUCCESS.getCode()) {
//                    PageBean pageBean = result.getData();
//                    if (pageBean.getPageNum() != pageBean.getNextPage()) {
//                        findProductByParam(typeId, pageBean.getNextPage(), pageBean.getPageSize(), false);
//                    }
//
//                } else {
//                    materialRefreshLayout.finishRefreshLoadMore();
//                }
            }
        });
    }

    private void loadParams() {
        //

//        List<Param> result = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            Param param = new Param(123, 456, "sfd", true, 33, 2, "", "");
//            result.add(param);
//        }
//        leftCategoryData.addAll(result);
//        findProductByParam(typeId, 1, 10, true);
//
//        categoryLeftAdapter.notifyDataSetChanged();
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
                        final Type type = new TypeToken<SverResponse<List<Param>>>(){}.getType();
                        SverResponse<List<Param>> result = JSONUtils.fromJson(response,type);
                        Toast.makeText(getActivity(),"参数shuliang"+result.getData().size(),Toast.LENGTH_LONG).show();
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() == null)
                                return;
                            leftCategoryData.addAll(result.getData());
                            categoryLeftAdapter.notifyDataSetChanged();
                            typeId=leftCategoryData.get(0).getId()+"";
                            leftCategoryData.get(0).setPressed(true);
                             findProductByParam(typeId,1,10,true);

                        }
                    }
                });
    }

    private void findProductByParam(String productTypeId, int pageNum, int pageSize, final boolean flag) {
        //
/*        List<Product> result1 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Product param = new Product();
            param.setStock(111);
            param.setStatus(2);
            param.setProductId(13);
            param.setPrice(new BigDecimal(1465));
            param.setName("chanpin1");
            param.setId(6545);
            param.setHot(2);
            param.setDetail("dfs");
            param.setSpecParam("sdfsdf");
            param.setPartsId(65465);
            result1.add(param);
        }
        rightProductData.addAll(result1);
        categoryRightAdapter.notifyDataSetChanged();*/

        OkHttpUtils.get()
                .url(Constant.API.CATEGORY_PRODUCT_URL)
                .addParams("productTypeId",productTypeId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(),"通过参数加载产品失败",Toast.LENGTH_LONG).show();
                        Log.e("产品加载",e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("产品加载","产品加载成功");
                        final Type type = new TypeToken<SverResponse<List<Product>>>(){}.getType();
                       result = JSONUtils.fromJson(response,type);
                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()) {
                            Toast.makeText(getActivity(),"a",Toast.LENGTH_LONG).show();
                            rightProductData.clear();
                            rightProductData.addAll(result.getData());
                            categoryRightAdapter.notifyDataSetChanged();
//                            if (result.getData() == null) {
//                                Toast.makeText(getActivity(),"b",Toast.LENGTH_LONG).show();
//                                if (flag){
//                                    rightProductData.clear();
//                                }
//                                Toast.makeText(getActivity(),"适配器数据更改",Toast.LENGTH_LONG).show();
//                                rightProductData.addAll(result.getData());
//                                categoryRightAdapter.notifyDataSetChanged();
//                            }
//                            if(!flag){
//                                refreshLayout.finishRefreshLoadMore();
//                            }
                    }
                    }
                });

    }

    private void loadSearch() {
        List<Product> result1 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Product param = new Product();
            param.setStock(111);
            param.setStatus(2);
            param.setProductId(13);
            param.setPrice(new BigDecimal(1465));
            param.setName("搜索的");
            param.setId(6545);
            param.setHot(2);
            param.setDetail("搜索的");
            param.setSpecParam("搜索的");
            param.setPartsId(65465);
            result1.add(param);
        }
        rightProductData.clear();
        rightProductData.addAll(result1);
        categoryRightAdapter.notifyDataSetChanged();

        //HTTP 请求要添加的参数好像不对，具体修改
        /*OkHttpUtils.get()
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
                       result = JSONUtils.fromJson(response,type);

                        if (result.getStatus()== ResponeCode.SUCCESS.getCode()) {
                            if (result.getData() == null) {
                                if (flag){
                                    rightProductData.clear();
                                }
                                rightProductData.addAll(result.getData().getData());
                                categoryRightAdapter.notifyDataSetChanged();
                            }
                            if(!flag){
                                refreshLayout.finishRefreshLoadMore();
                            }
                    }
                    }
                });*/
    }
}
