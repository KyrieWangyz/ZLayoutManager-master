package com.mcxtzhang.flowlayoutmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mcxtzhang.commonadapter.rv.ViewHolder;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.CoachMessageAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter.RecycleAdapter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Net.CoachmessageTask;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.CoachmessagePresenter;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoContract;
import com.mcxtzhang.flowlayoutmanager.R;
import com.mcxtzhang.flowlayoutmanager.swipecard.SwipeCardBeann;
import com.mcxtzhang.flowlayoutmanager.tantan.TanTanActivity;
import com.mcxtzhang.flowlayoutmanager.tantan.TanTanCallback;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/16 0016.
 */

public class CollectCoachActivity extends AppCompatActivity implements IpInfoContract.View_Coach{
    RecyclerView mRv;
    RecycleAdapter<SwipeCardBeann> mAdapter;
    List<SwipeCardBeann> mDatas;
    Button btn;
    
    

    private CoachmessagePresenter ipInfoPresenter;


    private IpInfoContract.Presenter mpresenter;
    private CoachInfon ipInfon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);


        CoachmessageTask trainTask=CoachmessageTask.getINSTANCE();
        ipInfoPresenter=new CoachmessagePresenter(this,trainTask);
        this.setPresenter(ipInfoPresenter);



        mpresenter.getIpInfo("20170915", NetUrl.ALLCOACH);
        
        
       

    }


    @Override
    public void setIpInfo(final CoachInfon ipInfon) {//当IpInfoPresenter中执行成功的那一步时候调用这个方法
        if(ipInfon!=null&&ipInfon.coach.get(0)!=null){
             String [] coach_name=new String[ipInfon.coach.size()];
            for (int i=0;i<ipInfon.coach.size();i++){
                coach_name[i]=ipInfon.coach.get(i).name;
            }
            init(coach_name);
        }
        else {
            Log.d("44", "showError: sda");
        }

    }

    private void init(String[] strings) {
        btn= (Button) findViewById(R.id.btnRefresh);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new OverLayCardLayoutManager());
        mRv.setAdapter(mAdapter = new RecycleAdapter<SwipeCardBeann>(this, mDatas = SwipeCardBeann.initDatas(strings), R.layout.item_swipe_card) {
            public static final String TAG = "zxt/Adapter";

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Log.d(TAG, "onCreateViewHolder() called with: parent = [" + parent + "], viewType = [" + viewType + "]");
                return super.onCreateViewHolder(parent, viewType);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                Log.d(TAG, "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
                super.onBindViewHolder(holder, position);
            }

            @Override
            public void convert(ViewHolder viewHolder, SwipeCardBeann SwipeCardBeann) {
                Log.d(TAG, "convert() called with: viewHolder = [" + viewHolder + "], SwipeCardBeann = [" + SwipeCardBeann + "]");
                viewHolder.setText(R.id.tvName, SwipeCardBeann.getName());
                viewHolder.setText(R.id.tvPrecent, SwipeCardBeann.getPostition() + " /" + mDatas.size());
                Picasso.with(CollectCoachActivity.this).load(SwipeCardBeann.getUrl()).into((ImageView) viewHolder.getView(R.id.iv));
            }
        });

        CardConfig.initConfig(this);

        final TanTanCallback callback = new TanTanCallback(mRv, mAdapter, mDatas);

        //测试竖直滑动是否已经不会被移除屏幕
        //callback.setHorizontalDeviation(Integer.MAX_VALUE);

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRv);
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
    public void setPresenter(IpInfoContract.Presenter presenter) {
        mpresenter=  presenter;
    }


}


