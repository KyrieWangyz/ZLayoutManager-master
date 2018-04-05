package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.RelativeLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.AAAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.SeconfFragmentAdapter;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.RollViewPagerAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.AppoinMessageInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.AppointTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.CoachmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.R;

import com.mcxtzhang.flowlayoutmanager.listener.OnCalendarChangedListener;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.utils.MyLog;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class SeconfFragment extends Fragment implements IpInfoContract.View_SearchByCoach ,View.OnClickListener{

    private RollPagerView rollPagerView;
    private Context mcontext;
    private ImageView img;
    private TextView text;
    private RecyclerView recyclerView;
    private IpInfoContract.Presenter mpresenter;
    private SeconfFragmentAdapter testAdapter;
    private AppoinMessageInfon ipInfon;
    private ImageView imageView;//收藏界面按钮，也就是第三个按钮
    private ImageView imageView1;//第一个按钮，就是搜索学院




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mcontext=getActivity();
        View root=inflater.inflate(R.layout.fragment_searchbycoachid,container,false);
        recyclerView= (RecyclerView) root.findViewById(R.id.recycle_view);

        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AppointTask trainTask=AppointTask.getINSTANCE();


        AppointmentSearchPresenter ipInfoPresenter = new AppointmentSearchPresenter(this, trainTask);
        this.setPresenter(ipInfoPresenter);


        StringBuffer sb=new StringBuffer(NetUrl.SEARCHAPPOINTBYCOACH);
        sb.append("coachID=81");
        String string=new String(sb);
        mpresenter.getIpInfo(null, string);
    }



    @Override
    public void setIpInfo(final AppoinMessageInfon ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.appointment.get(0)!=null){
            //recyclerView.setVisibility(View.VISIBLE);
            this.ipInfon=ipInfon;/////////////////////////////////这里我忘了加this过


            recyclerView.setLayoutManager((new
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL))
);
            //       testAdapter=new TestAdapter(mcontext,this.ipInfon.artical);//recyclerview的管理器
            //以下为图片三级缓存的测试
            testAdapter=new SeconfFragmentAdapter(mcontext,this.ipInfon.appointment,getActivity(),mpresenter);

            recyclerView.setAdapter(testAdapter);



        }
        else {
            Log.d("44", "showError: sda");
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        Log.d("44", "showError: sda");
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }



    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mpresenter=  presenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
