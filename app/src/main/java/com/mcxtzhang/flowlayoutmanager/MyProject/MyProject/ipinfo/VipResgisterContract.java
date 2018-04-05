package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.BaseView;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.VipResgisterInfo;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class VipResgisterContract {
    public interface Presenter{
        void getIpInfo(String name,String mailAddress,String address);
    }


    public interface View extends BaseView<VipResgisterContract.Presenter> {
        void setIpInfo(VipResgisterInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();


    }
}
