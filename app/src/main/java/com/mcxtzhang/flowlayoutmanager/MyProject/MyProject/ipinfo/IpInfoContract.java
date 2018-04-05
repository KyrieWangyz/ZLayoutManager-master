package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;


import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.BaseView;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.AppoinMessageInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainmessageInfo;

import java.io.File;

/**
 * Created by Administrator on 2017/11/26 0026.
 */

public class IpInfoContract {
    public interface Presenter{
        void getIpInfo(String date,String address);
    }//这个是用来post信息的


    public interface File_Presenter{
        void getIpInfo(File date, String address);
    }//这个是用来post上传文件的


    public interface View extends BaseView<Presenter> {
        void setIpInfo(IpInfon ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();

    }

    public interface View_Train extends BaseView<Presenter>{//这个是训练栏的
        void setIpInfo(TrainInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();

    }
    public interface View_Coach extends BaseView<Presenter> {//这个是教练的内容
        void setIpInfo(CoachInfon ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();

    }

    public interface View_Postmessage extends BaseView<Presenter> {//要post信息，不用绘制界面，仅仅接受是否成功的信息，统一用这个
        void setIpInfo(PostMessageInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();

    }

    public interface View_File_Postmessage extends BaseView<File_Presenter> {//要post信息，不用绘制界面，仅仅接受是否成功的信息，统一用这个
        void setIpInfo(PostMessageInfo ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();

    }





    public interface View_SearchByCoach extends BaseView<Presenter>{
        void setIpInfo(AppoinMessageInfon ipInfo);
        void showLoading();
        void hideLoading();
        void showError();
        boolean isActive();
    }



}
