package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.PosrmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.R;

import java.io.File;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class AddTrainFragmentnew extends Fragment implements  View.OnClickListener,IpInfoContract.View_Postmessage
{
    private IpInfoContract.Presenter presenter;
    private PostMessagePresent postMessagePresent;
    private EditText describe;
    private EditText parts;
    private Button button;
    private ImageView imageView;
    private Button selectImage;


    private String picPath = null;
    int request;
    private String filename;//这是上传的图片名字


    private IpInfoContract.File_Presenter mpresenter;
    private PostMessageInfo ipInfon;
    private PostFileMessagePresent ipInfoPresenter;

    private static final int MSG_CODE = 1001;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root;
        root=inflater.inflate(R.layout.fragment_add_train,container,false);
        init(root);
        setpresenter();





        return root;
    }

    private void init(View root) {
        describe= (EditText) root.findViewById(R.id.describe);
        parts= (EditText) root.findViewById(R.id.parts);
        button= (Button) root.findViewById(R.id.add);
        button.setOnClickListener(this);
        imageView= (ImageView) root.findViewById(R.id.img);

        selectImage = (Button) root.findViewById(R.id.selectImage);
        selectImage.setOnClickListener(this);
    }
    private void setpresenter() {
        PosrmessageTask posrmessageTask=PosrmessageTask.getINSTANCE();
        postMessagePresent=new PostMessagePresent(this,posrmessageTask);
        this.setPresenter(postMessagePresent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                String de=describe.getText().toString();
                String pa=parts.getText().toString();
                if(de!=null&&pa!=null) {
                    StringBuffer sb = new StringBuffer(NetUrl.ADDTRAIN);
                    sb.append("describe=");
                    sb.append(de);
                    sb.append("&parts=");
                    sb.append(pa);
                    String str=new String(sb);

                    postmessage(str);
                }
                else {
                    Toast.makeText(getActivity(),"请输入内容",Toast.LENGTH_SHORT).show();
                }
            case R.id.selectImage:
                /*** * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 */
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
            case R.id.uploadImage:
                if (picPath == null) {
                    Toast.makeText(getActivity(), "请选择图片！", Toast.LENGTH_SHORT).show();
                } else {
                    final File file = new File(picPath);
                    filename=file.getName();


                    if (file != null) {
                        mpresenter.getIpInfo(file,NetUrl.ADDTRAIN);
                    }

                }
                break;
            default:
                break;

        }
    }

    private void postmessage(String str) {
        presenter.getIpInfo(null,str);
    }
    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        this.presenter=presenter;

    }

    @Override
    public void setIpInfo(PostMessageInfo ipInfo) {
        Toast.makeText(this.getActivity(),ipInfo.msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"有错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
