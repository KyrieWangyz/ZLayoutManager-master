package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

public class Select1Activity extends AppCompatActivity implements View.OnClickListener{
    private Button log;
    private Button res;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select1);
        log= (Button) findViewById(R.id.login);
        log.setOnClickListener(this);
        res= (Button) findViewById(R.id.resgister);
        res.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Select1Activity.this,SelectActivity.class);
        switch (v.getId()){
            case R.id.resgister:
                intent.putExtra("key","res");//给第二个登陆界面传值，判断是要登陆还是要注册

            break;
            case R.id.login:
                intent.putExtra("key","log");
                break;

        }
        startActivity(intent);
    }
}
