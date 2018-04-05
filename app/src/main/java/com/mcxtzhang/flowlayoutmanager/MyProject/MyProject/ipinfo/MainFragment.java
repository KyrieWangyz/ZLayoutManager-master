package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;



import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.CoachMessageAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.RollViewPagerAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.R;
import com.mcxtzhang.flowlayoutmanager.activity.CoachActivity;
import com.mcxtzhang.flowlayoutmanager.activity.CollectCoachActivity;
import com.mcxtzhang.flowlayoutmanager.activity.MyAppointmentActivity;


/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class MainFragment extends Fragment implements IpInfoContract.View_Coach ,View.OnClickListener{
/*
第一個fragment
 */
   private RollPagerView rollPagerView;
   private Context mcontext;
   private ImageView img;
   private TextView text;
   private RecyclerView recyclerView;
    private IpInfoContract.Presenter mpresenter;
    private CoachMessageAdapter testAdapter;
    private CoachInfon ipInfon;
    private ImageView imageView;//收藏界面按钮，也就是第三个按钮
    private ImageView imageView1;//第一个按钮，就是搜索学院
    private ImageView imageView2;//第二个按钮，个人预约情况

    private LinearLayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mcontext=getActivity();
        View root=inflater.inflate(R.layout.fragment_first,container,false);
        recyclerView= (RecyclerView) root.findViewById(R.id.recycle_view);
        rollPagerView=(RollPagerView) root.findViewById(R.id.rollpagerview);

        rollPagerView.setAnimationDurtion(500);//设置切换时间
        rollPagerView.setAdapter(new RollViewPagerAdapter(rollPagerView));//广告轮播的管理器

        imageView= (ImageView) root.findViewById(R.id.collection);
        imageView.setOnClickListener(this);
        imageView1= (ImageView) root.findViewById(R.id.search);
        imageView2= (ImageView) root.findViewById(R.id.message);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        return root;
    }
    public static MainFragment newInstance(){
        return  new MainFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mpresenter.getIpInfo("20170915", NetUrl.ALLCOACH);//這一句是進行網絡請求
    }


/*
當你網絡請求成功了，就調用下面這一個方法
 */
    @Override
    public void setIpInfo(final CoachInfon ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.coach.get(0)!=null){
            recyclerView.setVisibility(View.VISIBLE);
            this.ipInfon=ipInfon;/////////////////////////////////这里我忘了加this过
            layoutManager=new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            recyclerView.setLayoutManager(layoutManager);
     //       testAdapter=new TestAdapter(mcontext,this.ipInfon.artical);//recyclerview的管理器
            //以下为图片三级缓存的测试
            testAdapter=new CoachMessageAdapter(mcontext,this.ipInfon.coach,getActivity());

            recyclerView.setAdapter(testAdapter);
            testAdapter.setOnItemClickListener(new CoachMessageAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(View view , int position){
                    Intent intent=new Intent(getActivity(), CoachActivity.class);
                    StringBuffer sb=new StringBuffer();


                    sb.append(ipInfon.coach.get(position).adept);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).coachID);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).detailMsg);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).headPic);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).name);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).schoolTime);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).sex);
                    sb.append("|");
                    sb.append(ipInfon.coach.get(position).motto);



                    Bundle bundle = new Bundle();
                    bundle.putString("message", sb.toString());
                    bundle.putString("from","1");//让跳转的activity判断是哪个fragment传来的
                    intent.putExtras(bundle);
                    startActivity(intent);
                    Toast.makeText(getActivity(), ipInfon.coach.get(position).adept,Toast.LENGTH_SHORT).show();
                }
            });

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
        return isAdded();
    }



    @Override
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mpresenter=  presenter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.collection:
                Intent intent=new Intent(getActivity(), CollectCoachActivity.class);
                startActivity(intent);
            case R.id.search:
                Intent intent1=new Intent(getActivity(),CollectCoachActivity.class);
                startActivity(intent1);
                break;
            case R.id.message:
                Intent intent2=new Intent(getActivity(),MyAppointmentActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
