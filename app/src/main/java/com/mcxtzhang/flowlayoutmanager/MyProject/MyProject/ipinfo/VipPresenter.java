package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.VipResgisterInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.NetTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.NetTask1;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class VipPresenter implements VipResgisterContract.Presenter,LoadTaskCallBack<VipResgisterInfo> {
    private NetTask1 netTask;
    private VipResgisterContract.View addTaskView;
    public VipPresenter(VipResgisterContract.View addTaskView,NetTask1 netTask){
        this.netTask=netTask;
        this.addTaskView=addTaskView;
    }


    @Override
    public void getIpInfo(String name,String mail,String address) {
        netTask.excute(name,mail,this,address);
    }

    @Override
    public void onSuccess(VipResgisterInfo ipInfo) {
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
