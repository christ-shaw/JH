package com.puxiang.jumi.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.puxiang.jumi.R;
import com.puxiang.jumi.bean.OrderListItem;
import com.puxiang.jumi.ui.adapter.MyOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


/**
 *  Created by nick on 15/10/21.
 */
public class OrderFagment extends Fragment implements ObservableScrollViewCallbacks {

    private Toolbar mToolbar;
    private PtrFrameLayout frame;
    private MaterialHeader header;
    private ObservableRecyclerView mRecyclerView;
    private MyOrderAdapter mAdapter;
    private ObservableRecyclerView.LayoutManager mLayoutManager;
    //缓存
    private List<OrderListItem> mOrderListItems = new ArrayList<OrderListItem>();
    private int mCurrentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order,null);
        initViews(view);
        return view;
    }


    private void initViews(View view){

        Activity parentActivity = getActivity();

        mToolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
        frame = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        header = new MaterialHeader(parentActivity.getBaseContext());

        header.setPadding(0, 20, 0, 20);
        header.setPtrFrameLayout(frame);

        frame.setLoadingMinTime(1000);
        frame.setDurationToCloseHeader(300);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);

        frame.setDurationToClose(100);
        frame.setPinContent(true);

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return mRecyclerView.getCurrentScrollY() == 0;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout ptrFrameLayout) {
                 getNewsList(OrderFagment.this.getActivity(), mOrderListItems, mAdapter, mRecyclerView, mCurrentPage, false);
                 ptrFrameLayout.refreshComplete();
            }
        });

        mRecyclerView = (ObservableRecyclerView) view.findViewById(R.id.my_order_recycler_view);
        mLayoutManager = new LinearLayoutManager(parentActivity);

        //mRecyclerView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);

        //mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setScrollViewCallbacks(this);
       // mRecyclerView.setItemAnimator(newDefaultItemAnimator());


        //设置adapter
//        mAdapter = new MyOrderAdapter(getActivity(),mOrderListItems);
//        mRecyclerView.setAdapter(mAdapter);


        //得到数据
        getNewsList(this.getActivity(), mOrderListItems, mAdapter, mRecyclerView, 0, false);
    }



    public static OrderListItem createNewItem(String title, String info, String price)
    {
        OrderListItem orderListItem1 = new OrderListItem();
        orderListItem1.setTitle(title);
        orderListItem1.setContent(info);
        orderListItem1.setPrice(price+"元");
        orderListItem1.setStatus("2");
        orderListItem1.setDate("2015-10-26 10:33");
        return orderListItem1;
    }

    public static void getNewsList(Context context, List<OrderListItem> listItems, MyOrderAdapter adapter, ObservableRecyclerView recyclerView, int currentPage, boolean forced) {
        listItems.add(createNewItem("送泡面", "深夜12点女朋友饿了，寻5号楼女同学给308寝的xx送包泡面", "20"));
        listItems.add(createNewItem("送伞", "女朋友聊天在上自习，寻A市B校同学去102自习室送一把伞", "20"));
        listItems.add(createNewItem("拉萨明信片", "相识纪念日，寻拉萨大学的同学给女朋友寄一张明信片到南京大学", "30"));
        listItems.add(createNewItem("求床位", "下周有同学来北京玩，学校内寻求两个床位", "40"));
        listItems.add(createNewItem("介绍校况", "高三学生，寻学长介绍一下学校情况", "30"));
        listItems.add(createNewItem("求导师信息", "跨城市考研，了解xx导师信息。", "200"));
        listItems.add(createNewItem("组织活动", "毕业10周年聚会，请一位同学帮忙联系学校组织活动。", "200"));
        listItems.add(createNewItem("补考试题", "上学期高数挂科了，马上补考，寻补考试题。", "100"));
        listItems.add(createNewItem("面经", "明天华为二面，寻找一位拿到offer的同学，咨询面试技巧。", "100"));
        listItems.add(createNewItem("求联系方式", "求外语系美女联系方式，打赏100元。", "100"));
        listItems.add(createNewItem("求西藏拍照", "给女朋友惊喜，求西藏学友在布达拉宫旁边写字拍照", "200"));
        listItems.add(createNewItem("求钢琴兼职", "侄子要学习钢琴，求介绍广州音乐学院钢琴专业兼职", "30"));
        listItems.add(createNewItem("北京稻香村", "想吃稻香村点心，求北京同学买和邮寄，打赏五十元", "50"));
        listItems.add(createNewItem("快闪", "2月14号想给女友惊喜，求十个人快闪，每人打赏五十元。", "50"));
        listItems.add(createNewItem("聚活动", "家乐福开业，寻三十人参与，一小时20元。", "20"));
        listItems.add(createNewItem("电脑维修", "电脑坏了急用，求维修。", "100"));
        listItems.add(createNewItem("魔兽副本", "魔兽世界，求三人组队带刷副本。", "20"));
        listItems.add(createNewItem("图书馆占座", "明天早上8.30，求图书馆占两位置，打赏十元。", "10"));
        listItems.add(createNewItem("代上英语", "明天上午前两节英语课求同学代上", "10"));
        listItems.add(createNewItem("英语四级答案", "求英语四级答案", "500"));
        listItems.add(createNewItem("物理期末考题", "马上考试，求大学物理去年期末考题", "50"));
        listItems.add(createNewItem("代取快递", "308寝室寻人长期代取快递", "1"));
        listItems.add(createNewItem("带烟", "已经熄灯锁门，求一包香烟", "20"));
        listItems.add(createNewItem("买演唱会门票", "明天下午寻一同学帮忙排队买演唱会门票", "50"));
        listItems.add(createNewItem("借吉他", "哪位同学有吉他，借用三天", "30"));
        listItems.add(createNewItem("借自行车", "借自行车一天", "10"));
        listItems.add(createNewItem("代资料", "天下午有去深圳大学的同学吗？顺便帮忙代一份资料过去", "6"));
        listItems.get(0).setStatus("1");
        listItems.get(1).setStatus("1");


        int total = listItems.size();
        //不强制刷新时，如果此页已存在则直接从内存中加载
        if (!forced && total>0 ){
            //mAdapter.addNews(mOrderListItems);
            adapter = new MyOrderAdapter(context,listItems);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            return;
        }

        if(forced && listItems.size()>0){
            listItems.clear();
        }
//        LoadNewsListTask loadDataTask = new LoadNewsListTask(adapter,mNewsType,forced);
//        loadDataTask.execute(currentPage);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
//        ActionBar ab = getSupportActionBar();
//        if (ab == null) {
//            return;
//        }
//        if (scrollState == ScrollState.UP) {
//            if (ab.isShowing()) {
//                ab.hide();
//            }
//        } else if (scrollState == ScrollState.DOWN) {
//            if (!ab.isShowing()) {
//                ab.show();
//            }
//        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
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
