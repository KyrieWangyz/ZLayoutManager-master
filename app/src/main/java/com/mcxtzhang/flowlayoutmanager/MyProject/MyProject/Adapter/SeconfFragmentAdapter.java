package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.AppoinMessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.NetAddress.NetUrl;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo.IpInfoContract;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.AsyncImageLoader;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.ImageLoaderManager;
import com.mcxtzhang.flowlayoutmanager.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/19 0019.
 */

public class SeconfFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    //测试三级缓存的
    AsyncImageLoader loader;

    private SeconfFragmentAdapter.OnItemClickListener mOnItemClickListener = null;

    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<AppoinMessageInfo> mData;
    private IpInfoContract.Presenter presenter;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    static class ViewHoldern extends RecyclerView.ViewHolder{
        Button cancel;
        ImageView imageView;
        TextView coachid;
        TextView memberid;
        TextView schooltime;
        TextView time;

        public ViewHoldern(View view){
            super(view);
            imageView= (ImageView) view.findViewById(R.id.img);
            coachid= (TextView) view.findViewById(R.id.coachid);
            memberid= (TextView) view.findViewById(R.id.memberid);
            schooltime= (TextView) view.findViewById(R.id.schooltime);
            time= (TextView) view.findViewById(R.id.time);
            cancel= (Button) view.findViewById(R.id.cancel);

        }
    }


    public SeconfFragmentAdapter(Context context, ArrayList<AppoinMessageInfo> data){
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
    }

    //以下为图片三级缓存的测试
    public SeconfFragmentAdapter(Context context, ArrayList<AppoinMessageInfo> data, Activity activity, IpInfoContract.Presenter presenter){
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
        this.presenter=presenter;


        //for test
        loader = new AsyncImageLoader(activity);

        //将图片缓存至外部文件中
        loader.setCache2File(true); //false
        //设置外部缓存文件夹
        loader.setCachedDir(activity.getCacheDir().getAbsolutePath());
    }




    @Override
    public SeconfFragmentAdapter.ViewHoldern onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_appoint,parent,false);
        SeconfFragmentAdapter.ViewHoldern viewHolder=new SeconfFragmentAdapter.ViewHoldern(view);

        view.setOnClickListener((View.OnClickListener) this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {




        final AppoinMessageInfo ipInfo=mData.get(position);
        ((ViewHoldern)holder).coachid.setText(ipInfo.coachID);
        ((ViewHoldern)holder).memberid.setText(ipInfo.memberID);
        ((ViewHoldern)holder).schooltime.setText(ipInfo.schoolTime);
        ((ViewHoldern)holder).time.setText(ipInfo.time);

        ((ViewHoldern)holder).cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0,mData.size());

                StringBuffer sb=new StringBuffer(NetUrl.CANCELAPPOINTMENT);
                sb.append("coachID=");
                sb.append(ipInfo.coachID);
                sb.append("&memberID=");
                sb.append(ipInfo.memberID);
                sb.append("&time=");
                sb.append(ipInfo.time);
                String string=new String(sb);

                presenter.getIpInfo(null,string);
            }
        });

        holder.itemView.setTag(position);



    }
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setOnItemClickListener(SeconfFragmentAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}
