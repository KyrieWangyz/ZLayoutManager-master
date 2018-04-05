package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Test;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import cn.finalteam.okhttpfinal.RequestParams;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.MediaType;
import okhttp3.MultipartBody;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class upImage {

    final String url = "upload url";
    File file = new File("fileDir", "test.jpg");
    RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
    RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", "test.jpg", fileBody)

            .build();
    Request request = new Request.Builder()
            .url(url)
            .post(requestBody)
            .build();


    final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    OkHttpClient okHttpClient  = httpBuilder
            //设置超时
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();






//    okHttpClient.newCall(request).enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            Log.e(TAG, "uploadMultiFile() e=" + e);
//        }
//
//
//        @Override
//        public void onResponse(Call call, Response response) throws IOException {
//            Log.i(TAG, "uploadMultiFile() response=" + response.body().string());
//        }
//    });



}
