package com.puxiang.jumi.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.puxiang.jumi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *  Created by nick on 15/10/21.
 */
public class MeFagment extends Fragment {
    private GridView my_gridView_user;

    //资源文件
    private int[] pic_path = {R.drawable.completed, R.drawable.ongoing, R.drawable.published, R.drawable.evaluated};
    private String[] texts = {"已完成","进行中","我发布的","待评价"};
    private LinearLayout ll_user_life;
    private LinearLayout ll_user_members;
    private LinearLayout ll_user_store;
    private LinearLayout ll_user_opinion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_user_profile, null);
        initView(view);
        return view;
    }


    private void initView(View view) {

//        ((TextView) view.findViewById(R.id.tv_top_txtTitle)).setText("我的主页");
        ll_user_life = (LinearLayout) view.findViewById(R.id.ll_user_life);
        ll_user_members = (LinearLayout) view.findViewById(R.id.ll_user_members);
        ll_user_store = (LinearLayout) view.findViewById(R.id.ll_user_store);
        ll_user_opinion = (LinearLayout) view.findViewById(R.id.ll_user4);

        my_gridView_user = (GridView) view.findViewById(R.id.gridview);

        List<Map<String, Object>> lstImageItem = new ArrayList<Map<String, Object>>();
        for(int i = 0 ; i < pic_path.length; ++i)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("taskImage",pic_path[i]);//添加图像资源的ID
            map.put("taskText", texts[i]);//按序号做ItemText
            lstImageItem.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),lstImageItem,R.layout.item_task_status,
                                 new String[] {"taskImage","taskText"},
                                 new int[]{R.id.taskImage,R.id.taskText});
        my_gridView_user.setAdapter(simpleAdapter);

       // ll_user_life.setOnClickListener(this);
       // ll_user_members.setOnClickListener(this);
       //ll_user_store.setOnClickListener(this);
        //ll_user_opinion.setOnClickListener(this);


//        my_gridView_user = (GridView) view.findViewById(R.id.gridView_user);
//        my_gridView_user.setSelector(new ColorDrawable(Color.TRANSPARENT));
//         adapter_GridView = new Adapter_GridView(getActivity(), pic_path);
//        my_gridView_user.setAdapter(adapter_GridView);



    }

    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }
}
