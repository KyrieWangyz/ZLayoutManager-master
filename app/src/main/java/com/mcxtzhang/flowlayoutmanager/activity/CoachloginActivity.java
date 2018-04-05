package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mcxtzhang.flowlayoutmanager.MvpApplication;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.HomeActivity;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.HomeCoachActivity;

import com.mcxtzhang.flowlayoutmanager.R;


/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class CoachloginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_login);
        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(this);



//        CoachloginActivity.LeakThread leakThread = new CoachloginActivity.LeakThread();
//        leakThread.start();
    }


//    class LeakThread extends Thread {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(6 * 60 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, HomeCoachActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        RefWatcher refWatcher = MvpApplication.getRefWatcher(this);//1
//        refWatcher.watch(this);
//    }
}
