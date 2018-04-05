package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.PosrmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class AddTonicFragment extends Fragment implements  View.OnClickListener,IpInfoContract.View_Postmessage
{
    private IpInfoContract.Presenter presenter;
    private PostMessagePresent postMessagePresent;
    private EditText tonicName;
    private EditText effect;
    private EditText eatMethod;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root;
        root=inflater.inflate(R.layout.fragment_add_tonic,container,false);
        init(root);
        setpresenter();
        return root;
    }

    private void init(View root) {
        tonicName= (EditText) root.findViewById(R.id.tonicName);
        effect= (EditText) root.findViewById(R.id.effect);
        eatMethod= (EditText) root.findViewById(R.id.eatMethod);
        button= (Button) root.findViewById(R.id.add);
        button.setOnClickListener(this);
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
                String ea=eatMethod.getText().toString();
                String ef=effect.getText().toString();
                String name=tonicName.getText().toString();
                String tonicpic="http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg";
                if(ea!=null&&ef!=null&&name!=null&&tonicpic!=null) {
                    StringBuffer sb = new StringBuffer(NetUrl.ADDTONIC);
                    sb.append("eatMethod=");
                    sb.append(ea);
                    sb.append("&effect=");
                    sb.append(ef);
                    sb.append("&tonicName=");
                    sb.append(name);
                    sb.append("&tonicPic=");
                    sb.append(tonicpic);
                    String str=new String(sb);

                    postmessage(str);
                }
                else {
                    Toast.makeText(getActivity(),"请输入内容",Toast.LENGTH_SHORT).show();
                }

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
