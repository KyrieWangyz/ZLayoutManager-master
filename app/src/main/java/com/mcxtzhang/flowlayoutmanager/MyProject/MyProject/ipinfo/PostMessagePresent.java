package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.NetTask;

/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class PostMessagePresent implements IpInfoContract.Presenter,LoadTaskCallBack<PostMessageInfo> {
    private NetTask netTask;
    private IpInfoContract.View_Postmessage addTaskView;
    public PostMessagePresent(IpInfoContract.View_Postmessage addTaskView,NetTask netTask){
        this.netTask=netTask;
        this.addTaskView=addTaskView;
    }


    @Override
    public void getIpInfo(String date,String address) {
        netTask.excute(date,this,address);
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
}
