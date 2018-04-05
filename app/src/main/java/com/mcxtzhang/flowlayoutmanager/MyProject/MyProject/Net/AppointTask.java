package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.AppoinMessageInfon;

import java.io.File;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/2/19 0019.
 */

public class AppointTask implements NetTask<String> {
    private static AppointTask INSTANCE=null;
    //  private static final String HOST="http://119.29.245.39/foodApp/interface/getArticalByDate.php";

    //   private LoadTaskCallBack loadTaskCallBack;//////////////////////////////////////////////////////////////完全不明白这东西存在的意义

    private AppointTask(){
    }
    public static AppointTask getINSTANCE(){
        if(INSTANCE==null)
        {
            INSTANCE=new AppointTask();
        }
        return  INSTANCE;
    }

    @Override
    public void excute(String date, final LoadTaskCallBack loadTaskCallBack, String address) {

        RequestParams requestParams=new RequestParams();
        requestParams.addFormDataPart("date",date);







        HttpRequestpost(address,requestParams,loadTaskCallBack);


    }

    private void HttpRequestpost(String address,RequestParams requestParams,final  LoadTaskCallBack loadTaskCallBack) {

        HttpRequest.post(address,requestParams,new BaseHttpRequestCallback<AppoinMessageInfon>(){
            @Override
            public void onStart() {
                super.onStart();
                loadTaskCallBack.onStart();
            }

            @Override
            protected void onSuccess(AppoinMessageInfon ipInfp) {
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
