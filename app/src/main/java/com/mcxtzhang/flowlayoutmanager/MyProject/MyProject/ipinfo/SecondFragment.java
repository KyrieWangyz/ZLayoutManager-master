package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.AAAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.SecondFragmentAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.SecondFragmentAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.CoachmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.PosrmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.R;
import com.mcxtzhang.flowlayoutmanager.activity.CoachActivity;
import com.mcxtzhang.flowlayoutmanager.listener.OnCalendarChangedListener;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.utils.MyLog;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/18 0018.
 */

public class SecondFragment extends Fragment implements OnCalendarChangedListener, com.necer.ncalendar.listener.OnCalendarChangedListener,IpInfoContract.View_Coach ,View.OnClickListener{
    private NCalendar ncalendar;
    private RecyclerView recyclerView;
    private TextView tv_month;
    private TextView tv_date;
    private Context mcontext;
    private String vipid;
    private ImageView imageView;
    private String data;
    private RadioButton m;
    private RadioButton a;
    private RadioButton e;
    private String time="1";
    private IpInfoContract.Presenter presenter;



    private SecondFragmentAdapter testAdapter;
    private CoachInfon ipInfon;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mcontext=getActivity();
        View root=inflater.inflate(R.layout.activity_ncalendar,container,false);

        getBundle();
        init(root);
        setpresenter();

        return root;
    }

    private void setpresenter() {
        CoachmessageTask trainTask=CoachmessageTask.getINSTANCE();


        CoachmessagePresenter ipInfoPresenter = new CoachmessagePresenter(this, trainTask);
        this.setPresenter(ipInfoPresenter);

//        getrecyclemessage();

    }

    private void init(View root) {
        ncalendar = (NCalendar) root.findViewById(R.id.ncalendarrrr);
        ncalendar.setOnClickListener(this);
        recyclerView = (RecyclerView)  root.findViewById(R.id.recyclerView);
        tv_month = (TextView) root. findViewById(R.id.tv_month);
        tv_date = (TextView) root. findViewById(R.id.tv_date);
        imageView= (ImageView) root. findViewById(R.id.sure);
        imageView.setOnClickListener(this);
        m= (RadioButton) root. findViewById(R.id.morning);
        a= (RadioButton) root. findViewById(R.id.afrterning);
        e= (RadioButton) root. findViewById(R.id.evening);
        m.setOnClickListener(this);
        // m.setBackgroundColor(R.color.white);
        a.setOnClickListener(this);

        e.setOnClickListener(this);

        //判断默认选择哪个单选按钮
        switch (time){
            case "1":
                m.setChecked(true);
                break;
            case "2":
                a.setChecked(true);
                break;
            case "3":
                e.setChecked(true);
                break;
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        AAAdapter aaAdapter = new AAAdapter(mcontext);
        recyclerView.setAdapter(aaAdapter);
        ncalendar.setOnCalendarChangedListener(this);

        ncalendar.post(new Runnable() {
            @Override
            public void run() {

                List<String> list = new ArrayList<>();
                list.add("2017-09-21");
                list.add("2017-10-21");
                list.add("2017-10-1");
                list.add("2017-10-15");
                list.add("2017-10-18");
                list.add("2017-10-26");
                list.add("2017-11-21");

                ncalendar.setPoint(list);
            }
        });
    }


    @Override
    public void onCalendarChanged(DateTime dateTime) {
        tv_month.setText(dateTime.getMonthOfYear() + "月");
        tv_date.setText(dateTime.getYear() + "年" + dateTime.getMonthOfYear() + "月" + dateTime.getDayOfMonth() + "日");
        MyLog.d("dateTime::" + dateTime);
        data=dateTime.toString();
        getrecyclemessage();
    }
    public void setDate(View view) {
        ncalendar.setDate("2017-12-31");
    }

    public void toMonth(View view) {
        ncalendar.toMonth();
    }

    public void toWeek(View view) {
        ncalendar.toWeek();
    }

    public void toToday(View view) {
        ncalendar.toToday();
    }

    public void toNextPager(View view) {
        ncalendar.toNextPager();
    }

    public void toLastPager(View view) {
        ncalendar.toLastPager();
    }


    public void setPoint(View view) {
        List<String> list = new ArrayList<>();
        list.add("2017-09-21");
        list.add("2017-10-21");
        list.add("2017-10-1");
        list.add("2017-10-15");
        list.add("2017-10-18");
        list.add("2017-10-26");
        list.add("2017-11-21");

        ncalendar.setPoint(list);
    }
    public void getBundle() {

        vipid= "1041";//教练先默认81，学生用1041


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.morning:
                time="1";
                getrecyclemessage();
                break;
            case R.id.afrterning:
                getrecyclemessage();
                time="2";
                break;
            case R.id.evening:
                getrecyclemessage();
                time="3";
                break;
        }
    }

    private void postmessage(String str) {
        presenter.getIpInfo(null,str);//第一个参数没有用，第二个是传接口链接
    }



    @Override
    public void setIpInfo(final CoachInfon ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.coach.get(0)!=null){
            recyclerView.setVisibility(View.VISIBLE);
            this.ipInfon=ipInfon;/////////////////////////////////这里我忘了加this过
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);
            //       testAdapter=new TestAdapter(mcontext,this.ipInfon.artical);//recyclerview的管理器
            //以下为图片三级缓存的测试
            testAdapter=new SecondFragmentAdapter(mcontext,this.ipInfon.coach,getActivity());

            recyclerView.setAdapter(testAdapter);
            testAdapter.setOnItemClickListener(new SecondFragmentAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(View view , int position){
                    Intent intent=new Intent(getActivity(), CoachActivity.class);
                    StringBuffer sb=new StringBuffer();


                    sb.append(ipInfon.coach.get(position).adept);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).coachID);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).detailMsg);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).headPic);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).name);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).schoolTime);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).sex);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).motto);

                    String[] strarray=data.split("T");


                    Bundle bundle = new Bundle();
                    bundle.putString("message", sb.toString());
                    bundle.putString("from","2");
                    bundle.putString("coachid",ipInfon.coach.get(position).coachID);
                    bundle.putString("school",time);
                    bundle.putString("time",strarray[0]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(getActivity(), ipInfon.coach.get(position).adept,Toast.LENGTH_SHORT).show();
                }
            });


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


        this.presenter=presenter;
    }

    public void getrecyclemessage() {
        String[] strarray=data.split("T");
        StringBuffer sb=new StringBuffer(NetUrl.SEARCHFORAPPOINT);
        sb.append("schoolTime=");
        sb.append(time);
        sb.append("&time=");
        sb.append(strarray[0]);
        String string=new String(sb);
        postmessage(string);
    }
}
