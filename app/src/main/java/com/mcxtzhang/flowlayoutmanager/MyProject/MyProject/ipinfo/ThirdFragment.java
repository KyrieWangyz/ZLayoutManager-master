package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;



import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.jude.rollviewpager.RollPagerView;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.RollViewPagerAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.TestAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.TrainAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainmessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.R;
import com.mcxtzhang.flowlayoutmanager.Rili.TestFragmentActivity;
import com.mcxtzhang.flowlayoutmanager.tantan.TanTanActivity;
import com.necer.ncalendar.adapter.MonthAdapter;


/**
 * Created by Administrator on 2017/12/9 0009.
 */

public class ThirdFragment extends Fragment implements IpInfoContract.View_Train {


    private FragmentPagerAdapter mAdapter;

private MonthAdapter monthAdapter;
    private Context mcontext;
    private ImageView img;
    private TextView text;
    private RecyclerView recyclerView;
    private IpInfoContract.Presenter mpresenter;
    private TrainAdapter testAdapter;
    private TrainInfo ipInfon;

    private LinearLayoutManager layoutManager;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mcontext=getActivity();
        View root=inflater.inflate(R.layout.fragment_third,container,false);
        recyclerView= (RecyclerView) root.findViewById(R.id.recycle_view);



        return root;
    }
    public static ThirdFragment newInstance(){
        return  new ThirdFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mpresenter.getIpInfo("20170915", NetUrl.TRAINMESSAGE);
    }



    @Override
    public void setIpInfo(TrainInfo ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.train!=null){
            recyclerView.setVisibility(View.VISIBLE);
            this.ipInfon=ipInfon;/////////////////////////////////这里我忘了加this过
            layoutManager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(new
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            //       testAdapter=new TestAdapter(mcontext,this.ipInfon.artical);//recyclerview的管理器
            //以下为图片三级缓存的测试
            testAdapter=new TrainAdapter(mcontext,this.ipInfon.train,getActivity());


            recyclerView.setAdapter(testAdapter);
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


    public int num(){
         int min=0;
         boolean b=true;//用于判断是否符合
         while(b){
             if(min%9==0||min%4==1||min%7==0||min%6==3){
                 b=false;
             }
             min+=9;
         }
        return min;
    }


}
