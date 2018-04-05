package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.HomeActivity;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class ViploginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_login);
        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
