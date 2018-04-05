package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.Model.IpInfon;
import com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.util.ImageLoaderManager;
import com.mcxtzhang.flowlayoutmanager.R;


/**
 * Created by Administrator on 2017/12/10 0010.
 */

public class TestDataAdapter extends ArrayAdapter<IpInfon> {
    private ImageLoaderManager mImagerLoader;

    public TestDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mImagerLoader = ImageLoaderManager.getInstance(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        IpInfon ipInfon=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.item_fragment_main,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.img= (ImageView) view.findViewById(R.id.img);
            viewHolder.textView= (TextView) view.findViewById(R.id.text);
            view.setTag(viewHolder);
        }
        else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(ipInfon.coach.get(position).name);
        mImagerLoader.displayImage(viewHolder.img,ipInfon.coach.get(position).headPic);


        return super.getView(position, convertView, parent);
    }
    class  ViewHolder {
        ImageView img;
        TextView textView;
    }
}
