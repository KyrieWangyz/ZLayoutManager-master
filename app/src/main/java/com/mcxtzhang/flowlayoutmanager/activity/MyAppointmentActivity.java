package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.SeconfFragmentAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.AppoinMessageInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.PostMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.AppointTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.PosrmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.AppointmentSearchPresenter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoContract;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.PostMessagePresent;
import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/2/19 0019.
 */

public class MyAppointmentActivity extends AppCompatActivity implements IpInfoContract.View_SearchByCoach ,View.OnClickListener,IpInfoContract.View_Postmessage{




    private Context mcontext;
    private ImageView img;
    private TextView text;
    private RecyclerView recyclerView;
    private IpInfoContract.Presenter mpresenter;
    private SeconfFragmentAdapter testAdapter;
    private AppoinMessageInfon ipInfon;
    private ImageView imageView;//收藏界面按钮，也就是第三个按钮
    private ImageView imageView1;//第一个按钮，就是搜索学院


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mcontext=this;
        setContentView(R.layout.fragment_searchbycoachid);
        recyclerView= (RecyclerView) findViewById(R.id.recycle_view);

        AppointTask trainTask = AppointTask.getINSTANCE();


        AppointmentSearchPresenter ipInfoPresenter = new AppointmentSearchPresenter(this, trainTask);
        this.setPresenter(ipInfoPresenter);


        StringBuffer sb = new StringBuffer(NetUrl.SEARCHAPPOINTBYVIPID);
        sb.append("memberID=1041");
        String string = new String(sb);
        mpresenter.getIpInfo(null, string);
    }

    @Override
    public void onResume() {
        super.onResume();




    }

   



    @Override
    public void setIpInfo( AppoinMessageInfon ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.appointment.get(0)!=null){
            //recyclerView.setVisibility(View.VISIBLE);
            this.ipInfon=ipInfon;/////////////////////////////////这里我忘了加this过


            recyclerView.setLayoutManager((new
                    StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL))
            );


            setpresenter();//在界面的网络数据加载完了之后，就把presenter设置成发送取消预约的那个presenter


            testAdapter=new SeconfFragmentAdapter(mcontext,this.ipInfon.appointment,this,mpresenter);

            recyclerView.setAdapter(testAdapter);


        }
        else {
            Log.d("44", "showError: sda");
        }
    }
    private void setpresenter() {
        PosrmessageTask posrmessageTask=PosrmessageTask.getINSTANCE();
        PostMessagePresent postMessagePresent = new PostMessagePresent(this, posrmessageTask);
        this.setPresenter(postMessagePresent);
    }


    @Override
    public void setIpInfo(PostMessageInfo ipInfo) {
        Toast.makeText(this,ipInfo.msg,Toast.LENGTH_SHORT);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        Toast.makeText(this.getApplicationContext(),"有错误",Toast.LENGTH_SHORT).show();;
    }

    @Override
    public boolean isActive() {
        return true;
    }



    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mpresenter=  presenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }


}
