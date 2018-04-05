package com.mcxtzhang.flowlayoutmanager.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.AAAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.PosrmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoContract;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoPresenter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.PostMessagePresent;
import com.mcxtzhang.flowlayoutmanager.R;
import com.mcxtzhang.flowlayoutmanager.listener.OnCalendarChangedListener;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.utils.MyLog;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/15 0015.
 */

public class VipOrderCoachActivity extends AppCompatActivity implements OnCalendarChangedListener, com.necer.ncalendar.listener.OnCalendarChangedListener,View.OnClickListener,IpInfoContract.View_Postmessage{
    private NCalendar ncalendar;
    private RecyclerView recyclerView;
    private TextView tv_month;
    private TextView tv_date;
    private Context mcontext;
    private String coachid;
    private ImageView imageView;
    private String data;
    private RadioButton m;
    private RadioButton a;
    private RadioButton e;
    private String time="1";
    private IpInfoContract.Presenter presenter;
    private PostMessagePresent postMessagePresent;

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mcontext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncalendar);
        getBundle();
        init();
        setpresenter();
    }

    private void setpresenter() {
        PosrmessageTask posrmessageTask=PosrmessageTask.getINSTANCE();
        postMessagePresent=new PostMessagePresent(this,posrmessageTask);
        this.setPresenter(postMessagePresent);
    }

    private void init() {
        ncalendar = (NCalendar) findViewById(R.id.ncalendarrrr);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_date = (TextView) findViewById(R.id.tv_date);
        imageView= (ImageView) findViewById(R.id.sure);
        imageView.setOnClickListener(this);
        m= (RadioButton) findViewById(R.id.morning);
        a= (RadioButton) findViewById(R.id.afrterning);
        e= (RadioButton) findViewById(R.id.evening);
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
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         coachid= bundle.getString("id");
         time=bundle.getString("schooltime");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sure:
                String[] strarray=data.split("T");
                Toast.makeText(this,strarray[0] ,Toast.LENGTH_SHORT).show();
                StringBuffer sb=new StringBuffer(NetUrl.VIPAPPCOACH);
                sb.append("coachID=");
                sb.append(coachid);
                sb.append("&memberID=");
                sb.append("1041");//由于后端接口还没写好，我这里都用1041这个id
                sb.append("&schoolTime=");
                sb.append(time);
                sb.append("&time=");
                sb.append(strarray[0]);
                String str=new String(sb);

                postmessage(str);

                break;
            case R.id.morning:
                time="1";
                break;
            case R.id.afrterning:
                time="2";
                break;
            case R.id.evening:
                time="3";
                break;
        }
    }

    private void postmessage(String str) {
        presenter.getIpInfo(null,str);//第一个参数没有用，第二个是传接口链接
    }

    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        this.presenter=presenter;

    }

    @Override
    public void setIpInfo(PostMessageInfo ipInfo) {
        Toast.makeText(this.getApplicationContext(),ipInfo.msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        Toast.makeText(this.getApplicationContext(),"有错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
