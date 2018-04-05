package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Test;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mcxtzhang.flowlayoutmanager.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/20 0020.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        findViewById(R.id.photo).setOnClickListener(this);


        findViewById(R.id.camear).setOnClickListener(this);


    }


    public void postFile(File file){



        // sdcard/dliao/aaa.jpg
        String path = file.getAbsolutePath() ;

        String [] split = path.split("\\/");


        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");


        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
//        file
                .addFormDataPart("imageFileName",split[split.length-1])
                .addFormDataPart("image",split[split.length-1],RequestBody.create(MEDIA_TYPE_PNG,file))
                .build();



        Request request = new Request.Builder().post(requestBody).url("http://qhb.2dyt.com/Bwei/upload").build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                System.out.println("response = " + response.body().string());

            }
        });



    }

    static final int INTENTFORCAMERA = 1 ;
    static final int INTENTFORPHOTO = 2 ;


    public String LocalPhotoName;
    public String createLocalPhotoName() {
        LocalPhotoName = System.currentTimeMillis() + "face.jpg";
        return LocalPhotoName ;
    }

    public void toCamera(){
        try {
            Intent intentNow = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri uri = null ;
//      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //针对Android7.0，需要通过FileProvider封装过的路径，提供给外部调用
//        uri = FileProvider.getUriForFile(UploadPhotoActivity.this, "com.bw.dliao", SDCardUtils.getMyFaceFile(createLocalPhotoName()));//通过FileProvider创建一个content类型的Uri，进行封装
//      }else {
            uri = Uri.fromFile(SDCardUtils.getMyFaceFile(createLocalPhotoName())) ;
//      }
            intentNow.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intentNow, INTENTFORCAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 打开相册
     */
    public void toPhoto(){
        try {
            createLocalPhotoName();
            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
            getAlbum.setType("image/*");
            startActivityForResult(getAlbum, INTENTFORPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case INTENTFORPHOTO:
                //相册
                try {
                    // 必须这样处理，不然在4.4.2手机上会出问题
                    Uri originalUri = data.getData();
                    File f = null;
                    if (originalUri != null) {
                        f = new File(SDCardUtils.photoCacheDir, LocalPhotoName);
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor actualimagecursor = this.getContentResolver().query(originalUri, proj, null, null, null);
                        if (null == actualimagecursor) {
                            if (originalUri.toString().startsWith("file:")) {
                                File file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                if(!file.exists()){
                                    //地址包含中文编码的地址做utf-8编码
                                    originalUri = Uri.parse(URLDecoder.decode(originalUri.toString(),"UTF-8"));
                                    file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                }
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        } else {
                            // 系统图库
                            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            actualimagecursor.moveToFirst();
                            String img_path = actualimagecursor.getString(actual_image_column_index);
                            if (img_path == null) {
                          //      postFile(f);//////////////////////////
                                InputStream inputStream = this.getContentResolver().openInputStream(originalUri);
                                FileOutputStream outputStream = new FileOutputStream(f);

                                copyStream(inputStream, outputStream);
                            } else {
                                File file = new File(img_path);
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        }
                        Bitmap bitmap = ImageResizeUtils.resizeImage(f.getAbsolutePath(), 1080);
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
                        if (bitmap != null) {

                            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                                fos.close();
                                fos.flush();
                            }
                            if (!bitmap.isRecycled()) {
                                bitmap.isRecycled();
                            }

                            System.out.println("f = " + f.length());
                            postFile(f);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }


                break;
            case INTENTFORCAMERA:
//        相机
                try {

                    //file 就是拍照完 得到的原始照片
                    File file = new File(SDCardUtils.photoCacheDir, LocalPhotoName);
                    Bitmap bitmap = ImageResizeUtils.resizeImage(file.getAbsolutePath(), 1080);
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
                    if (bitmap != null) {
                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                            fos.close();
                            fos.flush();
                        }
                        if (!bitmap.isRecycled()) {
                            //通知系统 回收bitmap
                            bitmap.isRecycled();
                        }
                        System.out.println("f = " + file.length());

                        postFile(file);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



                break;
        }

    }

    public static void copyStream(InputStream inputStream, OutputStream outStream) throws Exception {
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                inputStream.close();
            }
            if(outStream != null){
                outStream.close();
            }
        }

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.camear:
                toCamera();
                break;

            case R.id.photo:
                toPhoto();
                break;

        }
    }
}