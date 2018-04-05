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

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.CoachmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.IpInfoTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.AddFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.CoachmessagePresenter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.MainFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.SeconfFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.ThirdMainFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.TrainPresenter;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class HomeCoachActivity extends AppCompatActivity implements View.OnClickListener{

    private CoachmessagePresenter ipInfoPresenter;
    private FragmentManager fm;
    private MainFragment mainFragment ;
    private SeconfFragment seconfFragment;
    private AddFragment thirdFragment;
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
            case R.id.layout_counter:
                hideFragment(mainFragment,fragmentTransaction);
                hideFragment(thirdFragment,fragmentTransaction);

                if(seconfFragment==null){
                    seconfFragment = new SeconfFragment();
                    fragmentTransaction.add(R.id.fragment_content,seconfFragment);
                }else{
                    fragmentTransaction.show(seconfFragment);
                }
                break;
            case R.id.layout_shop:
                hideFragment(seconfFragment,fragmentTransaction);
                hideFragment(mainFragment,fragmentTransaction);

                if(thirdFragment==null){
                    thirdFragment=new AddFragment();
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
