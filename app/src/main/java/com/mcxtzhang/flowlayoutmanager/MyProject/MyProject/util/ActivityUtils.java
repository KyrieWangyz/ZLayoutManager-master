package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.MainFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.SeconfFragment;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.ThirdFragment;


/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class ActivityUtils {
    private MainFragment mainFragment;
    private SeconfFragment seconfFragment;
    private ThirdFragment thirdFragment;
    public static void addFragmentToActivity(FragmentManager fm, Fragment fragment, int frameid){

        FragmentTransaction fragmentTransaction
                =fm.beginTransaction();
        fragmentTransaction.add(frameid,fragment);
        fragmentTransaction.commit();





    }
}
