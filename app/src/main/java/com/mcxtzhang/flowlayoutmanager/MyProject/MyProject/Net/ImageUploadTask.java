package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.LoadTaskCallBack;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;

import java.io.File;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class ImageUploadTask implements NetTask<File> {
    private static ImageUploadTask INSTANCE=null;
    //  private static final String HOST="http://119.29.245.39/foodApp/interface/getArticalByDate.php";

    //   private LoadTaskCallBack loadTaskCallBack;//////////////////////////////////////////////////////////////完全不明白这东西存在的意义

    private ImageUploadTask(){
    }
    public static ImageUploadTask getINSTANCE(){
        if(INSTANCE==null)
        {
            INSTANCE=new ImageUploadTask();
        }
        return  INSTANCE;
    }

    @Override
    public void excute(File file, final LoadTaskCallBack loadTaskCallBack, String address) {
///////////////////
        final String url = "upload url";
      //  File file = new File("fileDir", "test.jpg");//第一个参数好像是路径，第二个是名字？？
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "test.jpg", fileBody)
                .build();
////////////////////
        RequestParams requestParams=new RequestParams();
      
        requestParams.addFormDataPart("",file,MediaType.parse("application/octet-stream"));//



        HttpRequestpost(address,requestParams,loadTaskCallBack);


    }

    private void HttpRequestpost(String address,RequestParams requestParams,final  LoadTaskCallBack loadTaskCallBack) {

        HttpRequest.post(address,requestParams,new BaseHttpRequestCallback<PostMessageInfo>(){
            @Override
            public void onStart() {
                super.onStart();
                loadTaskCallBack.onStart();
            }

            @Override
            protected void onSuccess(PostMessageInfo ipInfp) {
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
