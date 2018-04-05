package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.HomeCoachActivity;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.ImageUploadTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoContract;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.PostFileMessagePresent;
import com.mcxtzhang.flowlayoutmanager.R;

import java.io.File;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class CoachResgisterActivity extends AppCompatActivity implements IpInfoContract.View_File_Postmessage ,View.OnClickListener {

    private static String requestURL = "http://123.207.46.140:8080/facePot/pic";

    private String picPath = null;
    private Button selectImage;
    private Button post;
    int request;
    private String filename;//这是上传的图片名字
    private EditText adpet;
    private EditText yourname;
    private EditText detailMsg;
    private EditText schooltime;
    private EditText sex;
    private EditText motto;
    private ImageView imageView;


    private EditText tonicName;
    private EditText effect;
    private EditText eatMethod;
    private Button button;



    private IpInfoContract.File_Presenter mpresenter;
    private PostMessageInfo ipInfon;
    private PostFileMessagePresent ipInfoPresenter;

    private static final int MSG_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_resgister);
        selectImage = (Button) findViewById(R.id.selectImage);
        post = (Button) findViewById(R.id.post);
        selectImage.setOnClickListener(this);
        post.setOnClickListener(this);
        imageView = (ImageView) this.findViewById(R.id.imageView);
        adpet= (EditText) this.findViewById(R.id.adpet);
        detailMsg= (EditText) this.findViewById(R.id.detailMsg);
        yourname= (EditText) findViewById(R.id.yourname);
        schooltime= (EditText) findViewById(R.id.schooltime);
        sex= (EditText) findViewById(R.id.sex);
        motto= (EditText) findViewById(R.id.motto);

        ImageUploadTask trainTask=ImageUploadTask.getINSTANCE();
        ipInfoPresenter=new PostFileMessagePresent(this,trainTask);

        this.setPresenter(ipInfoPresenter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectImage:
                /*** * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 */
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.post:
                if (picPath == null) {
                    Toast.makeText(this, "请选择图片！", Toast.LENGTH_SHORT).show();
                } else {
                    final File file = new File(picPath);
                    filename=file.getName();


                    if (file != null) {

                        String ad=adpet.getText().toString();
                        String de=detailMsg.getText().toString();
                        String he=filename;
                        String mo=motto.getText().toString();
                        String name=yourname.getText().toString();
                        String se=sex.getText().toString();
                        String sc=schooltime.getText().toString();

                        if(ad!=null&&de!=null&&he!=null&&filename!=null&&mo!=null&&name!=null&&se!=null&&sc!=null) {
                            StringBuffer sb = new StringBuffer(NetUrl.COACHRESGISTER);
                            sb.append("adept=");
                            sb.append(adpet.getText().toString());
                            sb.append("&detailMsg=");
                            sb.append(detailMsg.getText().toString());
                            sb.append("&headPic=");
                            sb.append(filename);
                            sb.append("&motto=");
                            sb.append(motto.getText().toString());
                            sb.append("&name=");
                            sb.append(yourname.getText().toString());
                            sb.append("&sex=");
                            sb.append(sex.getText().toString());
                            sb.append("&schoolTime=");
                            sb.append(schooltime.getText().toString());
                            String str=new String(sb);
                            mpresenter.getIpInfo(file,requestURL);//結束

                        }



                    }

                }
                break;
            default:
                break;
        }


    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            /** * 当选择的图片不为空的话，在获取到图片的途径 */
            Uri uri = data.getData();
            Log.e("tag", "uri = " + uri);
            try {
                String[] pojo = { MediaStore.Images.Media.DATA };
                Cursor cursor = managedQuery(uri, pojo, null, null, null);
                if (cursor != null) {
                    ContentResolver cr = this.getContentResolver();
                    int colunm_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path = cursor.getString(colunm_index);
                    Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
                    /***
                     * * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
                     * * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
                     */
                    if (path.endsWith("jpg") || path.endsWith("png")) {
                        picPath = path;
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        imageView.setImageBitmap(bitmap);
                    } else {
                        alert();
                    }
                } else {
                    alert();
                }
            } catch (Exception e) {
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void alert() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您选择的不是有效的图片")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        picPath = null;
                    }
                }).create();
        dialog.show();
    }






    @Override
    public void setIpInfo(final PostMessageInfo ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.code!=null){
            //recyclerView.setVisibility(View.VISIBLE);
            this.ipInfon=ipInfon;/////////////////////////////////这里我忘了加this过


            Toast.makeText(this,this.ipInfon.code,Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(this, HomeCoachActivity.class);
            startActivity(intent);

        }
        else {
            Log.d("44", "showError: sda");
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        Log.d("44", "showError: sda");
    }

    @Override
    public boolean isActive() {
        return true;
    }





    @Override
    public void setPresenter(IpInfoContract.File_Presenter presenter) {
        mpresenter=presenter;
    }
}