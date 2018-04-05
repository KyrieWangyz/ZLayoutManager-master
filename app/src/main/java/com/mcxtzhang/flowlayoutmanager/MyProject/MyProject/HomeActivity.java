package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject;



import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;


import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.RecycleAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.CoachTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.CoachmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.IpInfoTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.CoachmessagePresenter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.MainFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.SecondMainFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.SecondFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.ThirdMainFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.TrainPresenter;
import com.mcxtzhang.flowlayoutmanager.R;


/**
 * Created by Administrator on 2017/12/16 0016.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{



    private CoachmessagePresenter ipInfoPresenter;
    private FragmentManager fm;
    private MainFragment mainFragment ;//第一個fragment
    private SecondFragment seconfFragment;//第二個fragemnt
    private ThirdMainFragment thirdFragment;//第三..
    private RelativeLayout r1;
    private RelativeLayout r2;
    private RelativeLayout r3;
    private Button button;

    private TrainPresenter coachPresenter;




    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_design);
        fm = getFragmentManager();//必须加在这里，ActivityUtils
        mainFragment= (MainFragment) fm.findFragmentById(R.id.fragment_content);
        init();

/*
幫你確定第一個顯示的是哪個fragment
 */


        if(mainFragment==null){
            mainFragment=MainFragment.newInstance();
            FragmentTransaction fragmentTransaction
                    =fm.beginTransaction();
            fragmentTransaction.add(R.id.fragment_content,mainFragment);
            fragmentTransaction.commit();
        }
        CoachmessageTask trainTask=CoachmessageTask.getINSTANCE();


        ipInfoPresenter=new CoachmessagePresenter(mainFragment,trainTask);
        mainFragment.setPresenter(ipInfoPresenter);


        /////////////////////////////////////////////////




    }

    private void init() {
        r1= (RelativeLayout) findViewById(R.id.layout_home);
        r1.setOnClickListener(this);
        r2= (RelativeLayout) findViewById(R.id.layout_counter);
        r2.setOnClickListener(this);
        r3= (RelativeLayout) findViewById(R.id.layout_shop);
        r3.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.layout_home:
                hideFragment(seconfFragment,fragmentTransaction);
                hideFragment(thirdFragment,fragmentTransaction);

                if(mainFragment==null){
                    mainFragment=MainFragment.newInstance();
                    fragmentTransaction.add(R.id.fragment_content,mainFragment);
                    IpInfoTask trainTask=IpInfoTask.getINSTANCE();
                    ipInfoPresenter=new CoachmessagePresenter(mainFragment,trainTask);
                    mainFragment.setPresenter(ipInfoPresenter);
                    fragmentTransaction.add(R.id.fragment_content,mainFragment);
                }else{
                    fragmentTransaction.show(mainFragment);
                }
                break;
            case R.id.layout_counter://點擊了第二個按鈕
                /*
                所以下面就是幫你顯示第二個按鈕
                 */
                hideFragment(mainFragment,fragmentTransaction);
                hideFragment(thirdFragment,fragmentTransaction);

                if(seconfFragment==null){
                    seconfFragment = new SecondFragment();
                    fragmentTransaction.add(R.id.fragment_content,seconfFragment);
                }else{
                    fragmentTransaction.show(seconfFragment);
                }
                break;
                ////////////////////////////////
            case R.id.layout_shop://點擊了第三個按鈕
                /*
                顯示第三個fragemt的
                 */
                hideFragment(seconfFragment,fragmentTransaction);
                hideFragment(mainFragment,fragmentTransaction);

                if(thirdFragment==null){
                    thirdFragment=new ThirdMainFragment();
                    fragmentTransaction.add(R.id.fragment_content,thirdFragment);
//                    TrainTask trainTask=TrainTask.getINSTANCE();
//                    coachPresenter=new TrainPresenter(thirdFragment,trainTask);
//                    thirdFragment.setPresenter(coachPresenter);
                }else{
                    fragmentTransaction.show(thirdFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }


    private void hideFragment(Fragment fragment, FragmentTransaction ft){
        if(fragment != null){
            ft.hide(fragment);
        }
    }

}
