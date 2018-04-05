package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.NetTask;

import java.io.File;

/**
 * Created by Administrator on 2018/3/18 0018.
 */

public class PostFileMessagePresent implements IpInfoContract.File_Presenter,LoadTaskCallBack<PostMessageInfo> {
    private NetTask netTask;
    private IpInfoContract.View_File_Postmessage addTaskView;
    public PostFileMessagePresent(IpInfoContract.View_File_Postmessage addTaskView,NetTask netTask){
        this.netTask=netTask;
        this.addTaskView=addTaskView;
    }




    @Override
    public void onSuccess(PostMessageInfo ipInfo) {
        if(addTaskView.isActive()){
            addTaskView.setIpInfo(ipInfo);
        }
    }

    @Override
    public void onStart() {
        if(addTaskView.isActive()){
            addTaskView.showLoading();
        }
    }

    @Override
    public void onFailed() {
        addTaskView.showError();
        addTaskView.hideLoading();
    }

    @Override
    public void onFinish() {
        if(addTaskView.isActive()){
            addTaskView.hideLoading();
        }
    }

    @Override
    public void getIpInfo(File date, String address) {
        netTask.excute(date,this,address);
    }
}