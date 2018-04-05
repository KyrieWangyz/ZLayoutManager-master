package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.PosrmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoContract;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.PostMessagePresent;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.SeconfvipFragment;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/2/14 0014.
 */

public class CoachActivity extends AppCompatActivity implements View.OnClickListener,IpInfoContract.View_Postmessage {
    private ImageView imageView;
    private TextView id;
    private TextView name;
    private TextView detailMsg;
    private TextView schooltime;
    private TextView sex;
    private TextView motto;
    private TextView adpet;
    private  String message;
    private String coachid;
    private String time;
    private Button button;
    private String s;
    private String where;//判断是从哪个fragment跳转过来的
    private String when;//这个是记录选中的预约该教练的时间，几月几号那个
    private String getCoachid;
    private IpInfoContract.Presenter presenter;
    private PostMessagePresent postMessagePresent;

    public boolean ischange=false;//判断此fragment是否发生改变，需要另一个界面更新



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_message);
        init();
        getBundle();
        setmesage();
        setpresenter();
    }

    private void setpresenter() {
        PosrmessageTask posrmessageTask=PosrmessageTask.getINSTANCE();
        postMessagePresent=new PostMessagePresent(this,posrmessageTask);
        this.setPresenter(postMessagePresent);
    }
    private void setmesage() {
        String[] strarray=message.split("[|]");
        adpet.setText(strarray[0]);
        id.setText(strarray[1]);
        name.setText(strarray[4]);
        detailMsg.setText(strarray[2]);
        motto.setText(strarray[3]);
sex.setText(strarray[6]);
        schooltime(strarray[5]);
        s=strarray[5];
        coachid=strarray[1];
        time=strarray[5];
    }

    private void schooltime(String string) {
        switch (string){
            case "1":
                schooltime.setText("上午工作");
            case "2":
                schooltime.setText("下午工作");
            case "3":
                schooltime.setText("晚上工作");
        }
    }

    private void init() {
        imageView= (ImageView) findViewById(R.id.headPic);
        id= (TextView) findViewById(R.id.id);
        name= (TextView) findViewById(R.id.name);
        detailMsg= (TextView) findViewById(R.id.detailMsg);
        motto= (TextView) findViewById(R.id.motto);
        schooltime= (TextView) findViewById(R.id.schooltime);
        adpet= (TextView) findViewById(R.id.adpet);
        sex= (TextView) findViewById(R.id.sex);
        button= (Button) findViewById(R.id.appointment);
        button.setOnClickListener(this);

    }

    public void getBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        message= bundle.getString("message");
        where=bundle.getString("from");
        when=bundle.getString("time");
    }

    @Override
    public void onClick(View v) {
        switch (where){
            case "1":
                Intent intent=new Intent(this,VipOrderCoachActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("id",coachid);
                bundle.putString("schooltime",time);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case "2":

                StringBuffer sb=new StringBuffer(NetUrl.VIPAPPCOACH);
                sb.append("coachID=");
                sb.append(coachid);
                sb.append("&memberID=");
                sb.append("1041");//暂时用这个用户
                sb.append("&schoolTime=");
                sb.append(s);
                sb.append("&time=");
                sb.append(when);
                String str=new String(sb);
                postmessage(str);

                break;
        }
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
    private void postmessage(String str) {
        presenter.getIpInfo(null,str);//第一个参数没有用，第二个是传接口链接
    }
}
