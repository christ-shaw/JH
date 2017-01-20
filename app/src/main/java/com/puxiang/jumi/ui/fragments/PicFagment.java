package com.puxiang.jumi.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.puxiang.jumi.R;

import static com.puxiang.jumi.R.styleable.View;

public class PicFagment extends Fragment {
    private ImageView imageView=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_image_demo, null);
        imageView=(ImageView)view.findViewById(R.id.imageView);
        int picID = R.drawable.demo_discover;
        imageView.setImageResource(picID);
        int select = getArguments().getInt("select");
//        switch (select) {
//            case 1://发现
//                picID = R.drawable.demo_discover;
//                break;
//            case 2://发布
//                picID = R.drawable.demo_publish;
//                break;
//            case 3://消息
//                picID = R.drawable.demo_info;
//                break;
//            default:
//                break;
//        }
//        imageView.setImageResource(picID);
//        imageView.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//            }
//        });
    }
}