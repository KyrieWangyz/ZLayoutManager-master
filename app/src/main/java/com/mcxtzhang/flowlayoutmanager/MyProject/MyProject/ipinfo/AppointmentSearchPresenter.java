package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.AppoinMessageInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.NetTask;

/**
 * Created by Administrator on 2018/2/19 0019.
 */

public class AppointmentSearchPresenter implements IpInfoContract.Presenter,LoadTaskCallBack<AppoinMessageInfon> {
    private NetTask netTask;
    private IpInfoContract.View_SearchByCoach addTaskView;
    public AppointmentSearchPresenter(IpInfoContract.View_SearchByCoach addTaskView,NetTask netTask){
        this.netTask=netTask;
        this.addTaskView=addTaskView;
    }


    @Override
    public void getIpInfo(String date,String address) {
        netTask.excute(date,this,address);
    }

    @Override
    public void onSuccess(AppoinMessageInfon ipInfo) {
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
