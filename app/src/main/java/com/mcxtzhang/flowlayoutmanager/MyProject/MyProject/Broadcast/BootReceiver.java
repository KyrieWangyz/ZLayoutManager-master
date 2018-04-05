package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.HomeActivity;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class BootReceiver extends BroadcastReceiver {
    static final String action_boot ="android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive (Context context, Intent intent) {

        Log.i("charge start", "启动完成");

        if (intent.getAction().equals(action_boot)){

            Intent mBootIntent = new Intent(context, HomeActivity.class);
            // 下面这句话必须加上才能开机自动运行app的界面
            mBootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(mBootIntent);
        }
    }
}

