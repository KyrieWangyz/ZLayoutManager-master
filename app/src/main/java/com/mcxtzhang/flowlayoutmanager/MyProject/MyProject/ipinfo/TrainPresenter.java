package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.TrainTask;

/**
 * Created by Administrator on 2018/1/21 0021.
 */

public class TrainPresenter implements IpInfoContract.Presenter,LoadTaskCallBack<TrainInfo> {
    private TrainTask netTask;
    private IpInfoContract.View_Train addTaskView;
    public TrainPresenter(IpInfoContract.View_Train addTaskView,TrainTask netTask){
        this.netTask=netTask;
        this.addTaskView=addTaskView;
    }


    @Override
    public void getIpInfo(String name,String address) {
        netTask.excute(name,this,address);
    }

    @Override
    public void onSuccess(TrainInfo ipInfo) {
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
