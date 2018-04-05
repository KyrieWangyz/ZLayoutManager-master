package com.mcxtzhang.flowlayoutmanager.MyProject.MyProject.ipinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcxtzhang.flowlayoutmanager.R;

/**
 * Created by Administrator on 2018/2/14 0014.
 */

public class AppointmentFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_appointment,container,false);
        return view;
    }
}
