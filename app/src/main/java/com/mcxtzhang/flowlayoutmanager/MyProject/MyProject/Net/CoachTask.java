package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainInfo;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;

/**
 * Created by Administrator on 2018/1/21 0021.
 */
/*
教练信息注册
 */
public class CoachTask implements NetTask1<String> {
    private static CoachTask INSTANCE=null;
    //  private static final String HOST="http://119.29.245.39/foodApp/interface/getArticalByDate.php";

    //   private LoadTaskCallBack loadTaskCallBack;//////////////////////////////////////////////////////////////完全不明白这东西存在的意义

    private CoachTask(){
    }
    public static CoachTask getINSTANCE(){
        if(INSTANCE==null)
        {
            INSTANCE=new CoachTask();
        }
        return  INSTANCE;
    }

    @Override
    public void excute(String name, String mail, final LoadTaskCallBack loadTaskCallBack, String address) {
        RequestParams requestParams=new RequestParams();
        requestParams.addFormDataPart("name",name);
        requestParams.addFormDataPart("mailAddress",mail);

        HttpRequestpost(address,requestParams,loadTaskCallBack);

    }

    private void HttpRequestpost(String address,RequestParams requestParams,final  LoadTaskCallBack loadTaskCallBack) {

        HttpRequest.post(address,requestParams,new BaseHttpRequestCallback<TrainInfo>(){
            @Override
            public void onStart() {
                super.onStart();
                loadTaskCallBack.onStart();
            }

            @Override
            protected void onSuccess(TrainInfo ipInfp) {
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
