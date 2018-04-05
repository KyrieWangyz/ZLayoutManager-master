package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.CoachInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.AsyncImageLoader;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.ImageLoaderManager;
import com.mcxtzhang.flowlayoutmanager.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/14 0014.
 */

public class CoachMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    //测试三级缓存的
    AsyncImageLoader loader;

    private OnItemClickListener mOnItemClickListener = null;

    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<CoachInfo> mData;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    static class ViewHoldern extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView kind;
        public ViewHoldern(View view){
            super(view);
            imageView= (ImageView) view.findViewById(R.id.img);
            textView= (TextView) view.findViewById(R.id.name);
            kind= (TextView) view.findViewById(R.id.adept);

        }
    }


    public CoachMessageAdapter(Context context, ArrayList<CoachInfo> data){
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
    }

    //以下为图片三级缓存的测试
    public CoachMessageAdapter(Context context, ArrayList<CoachInfo> data, Activity activity){
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);


        //for test
        loader = new AsyncImageLoader(activity);

        //将图片缓存至外部文件中
        loader.setCache2File(true); //false
        //设置外部缓存文件夹
        loader.setCachedDir(activity.getCacheDir().getAbsolutePath());
    }




    @Override
    public CoachMessageAdapter.ViewHoldern onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainfragment_coachmessage,parent,false);
        CoachMessageAdapter.ViewHoldern viewHolder=new CoachMessageAdapter.ViewHoldern(view);

        view.setOnClickListener((View.OnClickListener) this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {




        CoachInfo ipInfo=mData.get(position);
        ((ViewHoldern)holder).textView.setText(ipInfo.name);
        ((ViewHoldern)holder).kind.setText(ipInfo.adept);

        //mImagerLoader.displayImage(((ViewHoldern)holder).imageView,ipInfo.avatar);



        loader.downloadImage(ipInfo.headPic, true/*false*/, new AsyncImageLoader.ImageCallback() {
            @Override
            public void onImageLoaded(Bitmap bitmap, String imageUrl) {
                if(bitmap != null){
                    ((CoachMessageAdapter.ViewHoldern)holder).imageView.setImageBitmap(bitmap);
                }else{
                    //下载失败，设置默认图片
                }
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
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}
