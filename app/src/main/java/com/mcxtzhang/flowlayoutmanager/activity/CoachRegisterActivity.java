package com.mcxtzhang.flowlayoutmanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.VipResgisterInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.VipAddTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.VipPresenter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.VipResgisterContract;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class CoachRegisterActivity extends AppCompatActivity implements View.OnClickListener,VipResgisterContract.View{
    private String code;
    private VipPresenter ipInfoPresenter;
    private Button button;
    private EditText name;
    EditText mail;
    private VipResgisterContract.Presenter mpresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_resgister);




        VipAddTask ipInfoTask=VipAddTask.getINSTANCE();
        ipInfoPresenter=new VipPresenter(this,ipInfoTask);
        setPresenter(ipInfoPresenter);
        init();
    }

    private void loadnet(String n,String m) {
        StringBuffer sb=new StringBuffer(NetUrl.AddVip);
        sb.append("name=");
        sb.append(n);
        sb.append("&mailAddress=");
        sb.append(m);
        String str=new String(sb);

        mpresenter.getIpInfo(n,m, str);//这里由于接口接受post不用到变量，而是在接口后面追加报文，所以n，m在括号内，而是直接加到addvip后面
    }

    private void init() {
        name= (EditText) findViewById(R.id.name_vip);
        mail= (EditText) findViewById(R.id.mailbox_vip);
        button= (Button) findViewById(R.id.confirm);
        button.setOnClickListener(this);



        String n=name.getText().toString();
        String m=mail.getText().toString();
        loadnet(n,m);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:

                break;
        }

    }


    @Override
    public void setIpInfo(VipResgisterInfo ipInfo) {
        if (ipInfo != null && ipInfo.code != null) {
            code = ipInfo.code;
            Toast.makeText(getApplicationContext(), ipInfo.code,Toast.LENGTH_SHORT).show();
        } else {

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
//        return isAdded();
        return true;
    }



    @Override
    public void setPresenter(VipResgisterContract.Presenter presenter) {
        mpresenter=  presenter;
    }
}
