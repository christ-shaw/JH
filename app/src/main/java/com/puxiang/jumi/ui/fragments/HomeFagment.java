package com.puxiang.jumi.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.puxiang.jumi.R;
import com.puxiang.jumi.base.App;
import com.puxiang.jumi.bean.Engine;
import com.puxiang.jumi.bean.OrderListItem;
import com.puxiang.jumi.ui.ProductListActivity;
import com.puxiang.jumi.ui.adapter.HomeMenuAdapter;
import com.puxiang.jumi.ui.adapter.HomeViewPagerAdapter;
import com.puxiang.jumi.ui.adapter.MyOrderAdapter;
import com.puxiang.jumi.ui.adapter.ProductRecyclerAdapter;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


/**
 * Created by nick on 15/10/21.
 */
public class HomeFagment extends Fragment{

    private BGABanner mDefaultBanner;
    private List<View> mDefaultViews;
    private LinePageIndicator mIndicator;
    private ViewPager viewPager;
    private Engine mEngine;
    private static int APP_PAGE_SIZE = 8;
    private ArrayList<GridView> array;
    private Toolbar mToolbar;

    private PtrFrameLayout frame;
    private MaterialHeader header;
    private ObservableRecyclerView mRecyclerView;
    private MyOrderAdapter mAdapter;
    private ObservableRecyclerView.LayoutManager mLayoutManager;
    private List<OrderListItem> mOrderListItems = new ArrayList<OrderListItem>();
    private int mCurrentPage = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mEngine = App.getInstance().getEngine();

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);

        initDefault(view);
        initViews(view);
        return view;
    }

    private void initDefault(View view) {
        mDefaultBanner = (BGABanner) view.findViewById(R.id.banner_main_default);
        mDefaultViews = getViews(5);
        mDefaultBanner.setViews(mDefaultViews);

       // BannerModel bannerModel = new BannerModel();
        List<String> imgs = new ArrayList<String>();
        imgs.add("res://" + this.getActivity().getPackageName() + "/" + R.mipmap.title1);
        imgs.add("res://" + this.getActivity().getPackageName() + "/" + R.mipmap.title2);
        imgs.add("res://" + this.getActivity().getPackageName() + "/" + R.mipmap.title3);
        imgs.add("res://" + this.getActivity().getPackageName() + "/" + R.mipmap.title4);
        imgs.add("res://" + this.getActivity().getPackageName() + "/" + R.mipmap.title5);

        List<String> tips = new ArrayList<String>();
        tips.add("江湖秘籍");
        tips.add("昭告天下");
        tips.add("生财有道");
        tips.add("人在江湖");
        tips.add("海纳百川");

//        mEngine.fiveItem().enqueue(new Callback<BannerModel>() {
//            @Override
//            public void onResponse(retrofit.Response<BannerModel> response) {
//                BannerModel bannerModel = response.body();
                SimpleDraweeView simpleDraweeView;
                for (int i = 0; i < mDefaultViews.size(); i++) {
                    simpleDraweeView = (SimpleDraweeView) mDefaultViews.get(i);
                    simpleDraweeView.setImageURI(Uri.parse(imgs.get(i)));

                    // 为每一页添加点击事件
                    final int finalPosition = i;
                    simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          //  Toast.makeText(App.getInstance(), "点击了第" + (finalPosition + 1) + "页", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), ProductListActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
                }
                mDefaultBanner.setTips(tips);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//           }
//        });
    }


    public void initViews(View view) {

        viewPager = (ViewPager)view.findViewById(R.id.myviewpager);

//        final PackageManager packageManager = getActivity().getPackageManager();
//        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        // get all apps
//        final List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);
//        // the total pages
//        final int PageCount = (int)Math.ceil(apps.size()/APP_PAGE_SIZE);


        final List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("name", "江湖救急");
        map.put("icon", R.mipmap.menu1);
        list.add(map);

        HashMap<String,Object> map2 = new HashMap<String,Object>();
        map2.put("name", "资源共享");
        map2.put("icon", R.mipmap.menu2);
        list.add(map2);

        HashMap<String,Object> map3 = new HashMap<String,Object>();
        map3.put("name", "兼职");
        map3.put("icon", R.mipmap.menu3);
        list.add(map3);

        HashMap<String,Object> map4 = new HashMap<String,Object>();
        map4.put("name", "培训学习");
        map4.put("icon", R.mipmap.menu4);
        list.add(map4);

        HashMap<String,Object> map5 = new HashMap<String,Object>();
        map5.put("name", "爱拼团");
        map5.put("icon", R.mipmap.menu5);
        list.add(map5);

        HashMap<String,Object> map6 = new HashMap<String,Object>();
        map6.put("name", "交友活动");
        map6.put("icon", R.mipmap.menu6);
        list.add(map6);

        HashMap<String,Object> map8 = new HashMap<String,Object>();
        map8.put("name", "二手");
        map8.put("icon", R.mipmap.menu8);
        list.add(map8);

        HashMap<String,Object> map9 = new HashMap<String,Object>();
        map9.put("name","求职面经");
        map9.put("icon", R.mipmap.menu8);
        list.add(map9);

        final int PageCount = 2;//(int)Math.ceil(list.size()/APP_PAGE_SIZE);
        array = new ArrayList<GridView>();
        for (int i=0; i<PageCount; i++) {
            GridView appPage = new GridView(getActivity());
            appPage.setAdapter(new HomeMenuAdapter(getActivity(), list, i));
            appPage.setNumColumns(4);
            appPage.setVerticalSpacing(30);
            appPage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    //Toast.makeText(App.getInstance(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
                }
            });
            array.add(appPage);
        }

        viewPager.setAdapter(new HomeViewPagerAdapter(getActivity(),array));
        mIndicator = (LinePageIndicator)view.findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);

        setBottomList(view);
    }

    private void setBottomList(View view) {
        Activity parentActivity = getActivity();


        mRecyclerView = (ObservableRecyclerView) view.findViewById(R.id.my_order_recycler_view_home);
        mLayoutManager = new LinearLayoutManager(parentActivity);

        //mRecyclerView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //mRecyclerView.setScrollViewCallbacks(this);
        // mRecyclerView.setItemAnimator(newDefaultItemAnimator());


        //设置adapter
        mAdapter = new MyOrderAdapter(getActivity(),mOrderListItems);
        mRecyclerView.setAdapter(mAdapter);


        //得到数据
        OrderFagment.getNewsList(this.getActivity(), mOrderListItems, mAdapter, mRecyclerView, 0, false);

    }

    private List<View> getViews(int count) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            views.add(getActivity().getLayoutInflater().inflate(R.layout.view_image, null));
        }
        return views;
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }


}


