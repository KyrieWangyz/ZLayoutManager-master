package com.mcxtzhang.flowlayoutmanager;

import android.app.Application;
import android.content.Context;


import com.github.moduth.blockcanary.BlockCanary;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Test.AppBlockCanaryContext;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;


/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class MvpApplication extends Application {

 //   private RefWatcher refWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
 //       LeakCanary.install(this);

//        if (LeakCanary.isInAnalyzerProcess(this)) {//1
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }








        OkHttpFinalConfiguration.Builder builder=new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());//okhttpfinal申明


//        if (LeakCanary.isInAnalyzerProcess(this)) {//1
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//        refWatcher= setupLeakCanary();


        BlockCanary.install(this, new AppBlockCanaryContext()).start();//这个是检测UI卡顿的BlockCanary

    }
//    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//
//    public static RefWatcher getRefWatcher(Context context) {
//        MvpApplication leakApplication = (MvpApplication) context.getApplicationContext();
//        return leakApplication.refWatcher;
//    }


}
