package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Test.CoachResgisterActivity;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt1;
    private Button bt2;
    private String v;//判断是登陆还是注册
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        getbundle();
        bt1= (Button) findViewById(R.id.vip);
        bt2= (Button) findViewById(R.id.coach);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vip:
                Intent intent3=new Intent(this,ViploginActivity.class);
                Intent intent=new Intent(this,VipregisterActivity.class);
                if(v.equals("res"))
                startActivity(intent);
                else
                    startActivity(intent3);
                break;
            case R.id.coach:
                Intent intent4=new Intent(this,CoachloginActivity.class);
                Intent intent1=new Intent(this,CoachResgisterActivity.class);
                if (v.equals("log"))
                    startActivity(intent4);
                else

                startActivity(intent1);
                break;
        }
    }

    public void getbundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        v= bundle.getString("key");
    }
}
