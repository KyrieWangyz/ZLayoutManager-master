package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;


import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.NetTask;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class IpInfoPresenter implements IpInfoContract.Presenter,LoadTaskCallBack<IpInfon> {
    private NetTask netTask;
    private IpInfoContract.View addTaskView;
    public IpInfoPresenter(IpInfoContract.View addTaskView,NetTask netTask){
        this.netTask=netTask;
        this.addTaskView=addTaskView;
    }


    @Override
    public void getIpInfo(String date,String address) {
        netTask.excute(date,this,address);
    }

    @Override
    public void onSuccess(IpInfon ipInfo) {
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
