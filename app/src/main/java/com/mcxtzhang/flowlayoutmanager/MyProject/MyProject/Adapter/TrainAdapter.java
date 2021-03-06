package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.TrainmessageInfo;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.AsyncImageLoader;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.ImageLoaderManager;
import com.mcxtzhang.flowlayoutmanager.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/21 0021.
 */

public class TrainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //测试三级缓存的
    AsyncImageLoader loader;



    private ImageLoaderManager mImagerLoader;//异步图片加载工具
    private LayoutInflater mInflate;
    private Context mContext;
    private ArrayList<TrainmessageInfo> mData;

    static class ViewHoldern extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView describse;
        TextView id;
        TextView parts;
        public ViewHoldern(View view){
            super(view);


            imageView= (ImageView) view.findViewById(R.id.img);
            id= (TextView) view.findViewById(R.id.cardid);
            describse= (TextView) view.findViewById(R.id.describe);
            parts= (TextView) view.findViewById(R.id.parts);
        }
    }


    public TrainAdapter(Context context, ArrayList<TrainmessageInfo> data){
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);
        mImagerLoader = ImageLoaderManager.getInstance(mContext);
    }

    //以下为图片三级缓存的测试
    public TrainAdapter(Context context, ArrayList<TrainmessageInfo>data, Activity activity){
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
    public TrainAdapter.ViewHoldern onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_recyclerview,parent,false);
        ViewHoldern viewHolder=new ViewHoldern(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {




        TrainmessageInfo ipInfo=mData.get(position);
        ((TrainAdapter.ViewHoldern)holder).id.setText(ipInfo.trainID);
        ((ViewHoldern)holder).describse.setText(ipInfo.describe);
        ((ViewHoldern)holder).parts.setText(ipInfo.parts);
        //mImagerLoader.displayImage(((ViewHoldern)holder).imageView,ipInfo.avatar);



//        loader.downloadImage(ipInfo.avatar, true/*false*/, new AsyncImageLoader.ImageCallback() {
//            @Override
//            public void onImageLoaded(Bitmap bitmap, String imageUrl) {
//                if(bitmap != null){
//                    ((TestAdapter.ViewHoldern)holder).imageView.setImageBitmap(bitmap);
//                }else{
//                    //下载失败，设置默认图片
//                }
//            }
//        });


    }





    @Override
    public int getItemCount() {
        return mData.size();
    }


}
