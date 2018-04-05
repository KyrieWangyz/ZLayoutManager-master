package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainInfo;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by Administrator on 2018/2/14 0014.
 */

/*
教练信息汇总
 */
public class CoachmessageTask implements NetTask<String> {
    private static CoachmessageTask INSTANCE=null;
    //  private static final String HOST="http://119.29.245.39/foodApp/interface/getArticalByDate.php";

    //   private LoadTaskCallBack loadTaskCallBack;//////////////////////////////////////////////////////////////完全不明白这东西存在的意义

    private CoachmessageTask(){
    }
    public static CoachmessageTask getINSTANCE(){
        if(INSTANCE==null)
        {
            INSTANCE=new CoachmessageTask();
        }
        return  INSTANCE;
    }

    @Override
    public void excute(String date, final LoadTaskCallBack loadTaskCallBack,String address) {
        RequestParams requestParams=new RequestParams();
        requestParams.addFormDataPart("date",date);

        HttpRequestpost(address,requestParams,loadTaskCallBack);

    }

    private void HttpRequestpost(String address,RequestParams requestParams,final  LoadTaskCallBack loadTaskCallBack) {

        HttpRequest.post(address,requestParams,new BaseHttpRequestCallback<CoachInfon>(){
            @Override
            public void onStart() {
                super.onStart();
                loadTaskCallBack.onStart();
            }

            @Override
            protected void onSuccess(CoachInfon ipInfp) {
                super.onSuccess(ipInfp);
                loadTaskCallBack.onSuccess(ipInfp);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                loadTaskCallBack.onFailed();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadTaskCallBack.onFinish();
            }
        });
    }
}
